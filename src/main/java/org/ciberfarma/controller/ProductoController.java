package org.ciberfarma.controller;

import org.ciberfarma.model.Producto;
import org.ciberfarma.repository.IProductoRepository;
import org.ciberfarma.repository.IProveedorRepository;
import org.ciberfarma.repository.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductoController {

	@Autowired
	private IProductoRepository repo;
	
	@Autowired
	private ICategoriaRepository cat;
	
	@Autowired
	private IProveedorRepository prov;
	
	@GetMapping("/listar")
	public String listadoProducto(Model model) {
		model.addAttribute("lstProductos", repo.findAll());
		return "listado";
	}
	
	@GetMapping("/")
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
		return "redirect:/";
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
	
}
