package com.tcc.cantinaDigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagamentoController {
	
	@GetMapping("/escolhaPagamento")
	public String escolhaPagamento() {
		return "EscolhaPagamento";
	}
}