package org.ciberfarma.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.ciberfarma.model.Categoria;
import org.ciberfarma.model.Producto;
import org.ciberfarma.repository.IProductoRepository;
import org.ciberfarma.repository.IProveedorRepository;
import org.ciberfarma.repository.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ProductoController {

	@Autowired
	private IProductoRepository repo;
	
	@Autowired
	private ICategoriaRepository cat;
	
	@Autowired
	private IProveedorRepository prov;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private DataSource dataSource;
	
	@GetMapping("/listar")
	public String listadoProducto(Model model) {
		model.addAttribute("lstProductos", repo.findAll());
		return "listado";
	}
	
	@GetMapping("/nuevo")
	public String cargarPag(Model model) {
		Producto prod = new Producto();
		model.addAttribute("producto", prod);
		model.addAttribute("lstCategorias", cat.findAll());
		return "crudproductos";
	}
	
	@PostMapping("/grabar")
	public String grabarPag(@ModelAttribute Producto producto, RedirectAttributes attribute) {
		if(repo.save(producto) != null) {
			attribute.addFlashAttribute("success", "Registrado con éxito!");
		}else {
			attribute.addFlashAttribute("unsuccess", "Error registrando!");
		}
		return "redirect:/nuevo";
	}
	
	@PostMapping("/actualizar")
	public String actualizarPag(@ModelAttribute Producto producto, RedirectAttributes attribute) {
		if(repo.save(producto) != null) {
			attribute.addFlashAttribute("success", "Actualizado con éxito!");
		}else {
			attribute.addFlashAttribute("unsuccess", "Error actualizando!");
		}
		return "redirect:/editar/"+producto.getCodigo();
	}
	
	@GetMapping("/editar/{codigo}")
	public String editar(@ModelAttribute Producto producto, Model model) {
		model.addAttribute("producto", repo.findByCodigo(producto.getCodigo()));
		model.addAttribute("lstCategorias", cat.findAll());
		model.addAttribute("lstProveedores", prov.findAll());
		return "editar";
	}

	@PostMapping("/eliminar")
	public String eliminar(@ModelAttribute Producto producto, RedirectAttributes attribute) {
		Producto prod = repo.findByCodigo(producto.getCodigo());
		repo.delete(prod);
		attribute.addFlashAttribute("success","Eliminado con éxito!");		
		return "redirect:/listar";
	}
	
	@GetMapping("/reporte")
	public String reporte(Model model) {
		List<Categoria> listaCategorias = cat.findAll();
		model.addAttribute("listaCategorias", listaCategorias);
		return "reporte";
	}
	
	@PostMapping(value="/reporte", params="listar")
	public String listarReporte(@RequestParam(name="idCategoria") int idCategoria, Model model) {
		List<Categoria> listaCategorias = cat.findAll();
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(idCategoria);
		List<Producto> listaProductos = repo.findByCategoria(categoria);
		model.addAttribute("listaProductos", listaProductos);
		model.addAttribute("listaCategorias", listaCategorias);
		model.addAttribute("seleccion", idCategoria);
		return "reporteListar";
	}
	
	@PostMapping(value="/reporte", params="exportar")
	public void exportar(@RequestParam(name="idCategoria") int idCategoria, HttpServletResponse response) throws JRException, SQLException {
		String nombreReporte = "reporte_productos";
		response.setHeader("Content-Disposition", "inline; filename="+nombreReporte+".pdf");
		response.setContentType("application/pdf");
		try {
			String ru = resourceLoader.getResource("classpath:reportes/lista_productos.jasper").getURI().getPath();
			HashMap<String, Object>parametros = new HashMap<>();
			parametros.put("idCategoria", idCategoria);
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametros, dataSource.getConnection()); 
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
