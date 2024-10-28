package com.tcc.cantinaDigital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.cantinaDigital.model.Produto;
import com.tcc.cantinaDigital.model.Tipo;

public interface ProdutoRepository extends JpaRepository <Produto,Long>{
	List<Produto> findByTipo(Tipo tipo);
}