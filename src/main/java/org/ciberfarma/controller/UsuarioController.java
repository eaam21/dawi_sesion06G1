package org.ciberfarma.controller;

import org.ciberfarma.model.Usuario;
import org.ciberfarma.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

	@Autowired
	private IUsuarioRepository usu;
	
	@GetMapping("/login")
	public String formularioLogin(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "login";
	}
	
	@PostMapping("/validar")
	public String validarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes attribute) {
		Usuario usuarioValidar = usu.findByCorreoAndClave(usuario.getCorreo(), usuario.getClave());
		if(usuarioValidar!=null) {
			return "redirect:/listar";
		}else {
			attribute.addFlashAttribute("mensaje", "Error iniciando sesión.");
		}
		return "redirect:/login";
	}
}
