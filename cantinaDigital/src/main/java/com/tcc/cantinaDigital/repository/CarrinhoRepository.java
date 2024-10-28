package com.tcc.cantinaDigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tcc.cantinaDigital.model.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> { 
	
}