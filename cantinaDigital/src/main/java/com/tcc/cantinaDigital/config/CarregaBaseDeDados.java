package com.tcc.cantinaDigital.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tcc.cantinaDigital.model.Papel;
import com.tcc.cantinaDigital.model.Usuario;
import com.tcc.cantinaDigital.repository.UsuarioRepository;

@Configuration
public class CarregaBaseDeDados {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoderSenha;
	
	@Bean
	CommandLineRunner executar1() {
	    return args -> {
	        Papel papelAdm = new Papel();
	        papelAdm.setNomePapel("ROLE_ADM");

	        Set<Papel> papeis = new HashSet<>();
	        papeis.add(papelAdm);

	        Usuario adm = new Usuario();
	        adm.setNomeUsuario("adm");
	        adm.setEmail("adm@adm");
	        adm.setSenha(encoderSenha.encode("senha123"));
	        adm.setPapeis(papeis);
	        usuarioRepository.save(adm);
	    };
	}
}