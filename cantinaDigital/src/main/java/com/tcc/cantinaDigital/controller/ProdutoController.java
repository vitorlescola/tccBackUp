package com.tcc.cantinaDigital.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tcc.cantinaDigital.model.Produto;
import com.tcc.cantinaDigital.model.Tipo;
import com.tcc.cantinaDigital.model.Usuario;
import com.tcc.cantinaDigital.repository.ProdutoRepository;
import com.tcc.cantinaDigital.repository.UsuarioRepository;
import com.tcc.cantinaDigital.service.CarrinhoService;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private CarrinhoService carrinhoService;
	
	@GetMapping("/cadastrarProduto")
	public String cadastrarProduto(Model modelo) {
		modelo.addAttribute("produto", new Produto());
		return "CadastroProduto";
	}
	
	@PostMapping("/salvarProduto")
	public String salvarProduto(Produto produto) {
		produtoRepository.save(produto);
		return "redirect:/menuAdm";
	}
	
	@GetMapping("/listaLanches")
	public String listaLanches(Model modelo) {
	    modelo.addAttribute("produtos", produtoRepository.findByTipo(Tipo.Salgado));
	    return "ListaLanches";
	}
	
	@PostMapping("/adicionarAoCarrinho/{id}")
	public String adicionarAoCarrinho(@PathVariable("id") Long id) {
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String nomeUsuario = authentication.getName();
	    Usuario usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
	   
	    Optional<Produto> produtoOpt = produtoRepository.findById(id);
	    if (produtoOpt.isPresent()) {
	        System.out.println("Produto encontrado: " + produtoOpt.get().getNomeProduto());
	        carrinhoService.adicionarAoCarrinho(usuario.getIdUsuario(), produtoOpt.get());
	        System.out.println("Produto adicionado ao carrinho.");
	    } else {
	        System.out.println("Produto não encontrado com ID: " + id);
	    }
	   
	    return "redirect:/listaLanches";
	}
	
	@GetMapping("/LanchesAdm")
	public String LanchesAdm(Model modelo) {
		modelo.addAttribute("produtos", produtoRepository.findByTipo(Tipo.Salgado));
		return "LanchesAdm";
	}
	
	@GetMapping("/listaBebidas")
	public String listarBebidas(Model modelo) {
		modelo.addAttribute("produtos", produtoRepository.findByTipo(Tipo.Bebida));
		return "ListarBebidas";
	}
	
	@GetMapping("/bebidasAdm")
	public String bebidasAdm(Model modelo) {
		modelo.addAttribute("produtos", produtoRepository.findByTipo(Tipo.Bebida));
		return "BebidasAdm";
	}
	
	@GetMapping("/listaDoces")
	public String listarDoces(Model modelo) {
		modelo.addAttribute("produtos", produtoRepository.findByTipo(Tipo.Doce));
		return "ListarDoces";
	}
	
	@GetMapping("/docesAdm")
	public String docesAdm(Model modelo) {
		modelo.addAttribute("produtos", produtoRepository.findByTipo(Tipo.Doce));
		return "docesAdm";
	}
	
	@GetMapping("/menuPedidos")
	public String menuPedidos() {
		return "MenuPedidos";
	}
	
	@GetMapping("/menuAdm")
	public String menuAdm() {
		return "MenuAdm";
	}
	
	@GetMapping("/editarProduto/{id}")
	public String editarProduto(@PathVariable("id") Long id, Model modelo) {
	    Optional<Produto> produtoOpt = produtoRepository.findById(id);
	    
	    if(produtoOpt.isPresent()) {
	        modelo.addAttribute("produto", produtoOpt.get());
	        return "CadastroProduto";
	    } else {
	        modelo.addAttribute("errorMessage", "Produto não encontrado");
	        return "redirect:/menuAdm";
	    }
	}
	
	@GetMapping("/excluirProduto/{id}")
	public String excluirProduto(@PathVariable("id") Long id) {
		carrinhoService.removerProdutoDosCarrinhos(id);
		produtoRepository.deleteById(id);
		return "redirect:/menuAdm";
	}
}