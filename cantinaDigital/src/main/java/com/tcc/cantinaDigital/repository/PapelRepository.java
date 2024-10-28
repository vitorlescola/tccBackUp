package com.tcc.cantinaDigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.cantinaDigital.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long>{
	Papel findByNomePapel(String nomePapel);
}