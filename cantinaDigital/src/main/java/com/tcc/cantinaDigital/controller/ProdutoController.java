package com.tcc.cantinaDigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProdutoController {
	
	@GetMapping("/cadastrarProduto")
	public String cadastrarProduto() {
		return "CadastroProduto";
	}
	
	@GetMapping("/listaLanches")
	public String listaLanches() {
		return "ListaLanches";
	}
	
	@GetMapping("/listaBebidas")
	public String listarBebidas() {
		return "ListarBebidas";
	}
	
	@GetMapping("/listaDoces")
	public String listarDoces() {
		return "ListarDoces";
	}
	
	@GetMapping("/menuPedidos")
	public String menuPedidos() {
		return "MenuPedidos";
	}
}