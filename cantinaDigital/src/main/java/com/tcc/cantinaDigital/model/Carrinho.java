package com.tcc.cantinaDigital.model;

import java.util.HashMap;
import java.util.Map;
import jakarta.persistence.*;

@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "carrinho_produtos", joinColumns = @JoinColumn(name = "carrinho_id"))
    @MapKeyJoinColumn(name = "produtos_id")
    @Column(name = "quantidade")
    private Map<Produto, Integer> produtos = new HashMap<>();

    public Carrinho() { }

    public Carrinho(Long id, Map<Produto, Integer> produtos) {
        this.id = id;
        this.produtos = produtos;
    }
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<Produto, Integer> getProdutos() {
		return produtos;
	}

	public void setProdutos(Map<Produto, Integer> produtos) {
		this.produtos = produtos;
	}
}