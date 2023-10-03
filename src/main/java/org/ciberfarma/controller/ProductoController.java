package org.ciberfarma.controller;

import org.ciberfarma.model.Producto;
import org.ciberfarma.repository.IProductoRepository;
import org.ciberfarma.repository.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoController {

	@Autowired
	private IProductoRepository repo;
	
	@Autowired
	private ICategoriaRepository cat;
	
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
	public String grabarPag(@ModelAttribute Producto producto) {
		repo.save(producto);
		return "exito";
	}
	
	@GetMapping("/editar/{codigo}")
	public String editar(@ModelAttribute Producto producto, Model model) {
		model.addAttribute("producto", repo.findByCodigo(producto.getCodigo()));
		model.addAttribute("lstCategorias", cat.findAll());
		return "editar";
	}
}
