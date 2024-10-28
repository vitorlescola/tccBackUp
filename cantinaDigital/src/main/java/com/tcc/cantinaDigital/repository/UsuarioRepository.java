package com.tcc.cantinaDigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.cantinaDigital.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByNomeUsuario(String nomeUsuario);
	Usuario findByEmail(String email);
}