package com.tcc.cantinaDigital.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tcc.cantinaDigital.model.Papel;
import com.tcc.cantinaDigital.model.Usuario;
import com.tcc.cantinaDigital.repository.UsuarioRepository;

@Service
public class DetalhesUsuarioService implements UserDetailsService{
	 
	@Autowired
	private UsuarioRepository usuarioRepository;

	 @Override
	 public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
	     System.out.println("Buscando usuário: " + nomeUsuario);
	     Usuario usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
	     if(usuario == null) {
	         throw new UsernameNotFoundException("Usuário não encontrado");
	     }
	     return new org.springframework.security.core.userdetails.User(usuario.getNomeUsuario(),
	             usuario.getSenha(),
	             mapearPapeisParaPermissoes(usuario.getPapeis()));
	 }
	
	private Collection<? extends GrantedAuthority> mapearPapeisParaPermissoes(Set<Papel> papeis){
		return papeis.stream()
				.map(papel -> new SimpleGrantedAuthority(papel.getNomePapel()))
				.collect(Collectors.toList());
	}
}