package com.tcc.cantinaDigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String vazio() {
		return "login";
	}
	
	@GetMapping("/criarConta")
	public String criarConta() {
		return "CriarConta";
	}
}