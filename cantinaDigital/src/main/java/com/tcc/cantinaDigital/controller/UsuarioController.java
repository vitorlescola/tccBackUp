package com.tcc.cantinaDigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
	
	@GetMapping("/menuUsuario")
	public String menuUsuario() {
		return "MenuUsuario";
	}
	
	@GetMapping("/carrinho")
	public String carrinho() {
		return "Carrinho";
	}
	
	@GetMapping("/editarPerfil")
	public String editarPerfil() {
		return "EditarPerfil";
	}
}