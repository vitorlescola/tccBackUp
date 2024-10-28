package com.tcc.cantinaDigital.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tcc.cantinaDigital.model.Carrinho;
import com.tcc.cantinaDigital.model.Usuario;
import com.tcc.cantinaDigital.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private BCryptPasswordEncoder encoderSenha;
	
	@GetMapping("/menuUsuario")
	public String menuUsuario(Model modelo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName();
        
        Usuario usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        modelo.addAttribute("nomeUsuario", usuario.getNomeUsuario());
        modelo.addAttribute("emailUsuario", usuario.getEmail());
        modelo.addAttribute("idUsuario", usuario.getIdUsuario());

		return "MenuUsuario";
	}
	
	@GetMapping("/carrinho")
	public String carrinho(Model modelo) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String nomeUsuario = authentication.getName();
	    
	    Usuario usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
	    if (usuario.getCarrinho() != null) {
	        modelo.addAttribute("carrinho", usuario.getCarrinho());
	    } else {
	        modelo.addAttribute("carrinho", new Carrinho());
	    }
	    
	    return "Carrinho";
	}
	
	@PostMapping("/limparCarrinho")
	public String limparCarrinho() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String nomeUsuario = authentication.getName();
	    Usuario usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
	    
	    if (usuario.getCarrinho() != null) {
	        usuario.getCarrinho().getProdutos().clear();
	        usuarioRepository.save(usuario);
	    }
	    
	    return "redirect:/carrinho";
	}

	
	@GetMapping("/editarPerfil")
	public String editarPerfil(Model modelo) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String nomeUsuario = authentication.getName();
	    Usuario usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
	    modelo.addAttribute("usuario", usuario);
	    
	    return "EditarPerfil";
	}
	
	@PostMapping("/editarUsuario/{id}")
	public String editarUsuario(@PathVariable("id") Long id, @ModelAttribute Usuario usuario, Model modelo) {
	    Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
	    
	    if (usuarioOpt.isPresent()) {
	        Usuario usuarioExistente = usuarioOpt.get();
	        
	        
	        if (!usuario.getNomeUsuario().equals(usuarioExistente.getNomeUsuario()) &&
	            usuarioRepository.findByNomeUsuario(usuario.getNomeUsuario()) != null) {
	            modelo.addAttribute("mensagem", "Nome de usuário já existe!");
	            return "redirect:/editarPerfil";
	        }

	        
	        usuarioExistente.setNomeUsuario(usuario.getNomeUsuario());
	        usuarioExistente.setEmail(usuario.getEmail());
	        if (!usuario.getSenha().isEmpty()) {
	            usuarioExistente.setSenha(encoderSenha.encode(usuario.getSenha()));
	        }
	        usuarioRepository.save(usuarioExistente);
	        return "redirect:/login";
	    } else {
	        return "redirect:/menuUsuario";
	    }
	}
	
	@PostMapping("/excluirUsuario/{id}")
	public String excluirUsuario(@PathVariable("id") Long id, Model modelo) {
	    Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
	    
	    if (usuarioOpt.isPresent()) {
	        usuarioRepository.delete(usuarioOpt.get());
	        modelo.addAttribute("mensagem", "Usuário excluído com sucesso.");
	    } else {
	        modelo.addAttribute("mensagem", "Usuário não encontrado.");
	    }
	    
	    return "redirect:/login";
	}
	
	@GetMapping("/favoritos")
	public String favoritos() {
		return "Favoritos";
	}
	
	@GetMapping("/perfilAdm")
	public String perfilAdm(Model modelo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName();
        
        Usuario usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        modelo.addAttribute("nomeUsuario", usuario.getNomeUsuario());
        modelo.addAttribute("emailUsuario", usuario.getEmail());
        modelo.addAttribute("idUsuario", usuario.getIdUsuario());

		return "PerfilAdm";
	}
}