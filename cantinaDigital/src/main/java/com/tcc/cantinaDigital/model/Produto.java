package com.tcc.cantinaDigital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String nomeProduto;
	@Column(nullable=false)
	private String descriçao;
	@Column(nullable=false)
	private float preço;
	@Column(nullable=false)
	private int estoque;
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	
	
	public Produto() {
	}
	
	public Produto(Long id, String nomeProduto, String descriçao, float preço, int estoque, Tipo tipo) {
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.descriçao = descriçao;
		this.preço = preço;
		this.estoque = estoque;
		this.tipo = tipo;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getDescriçao() {
		return descriçao;
	}
	public void setDescriçao(String descriçao) {
		this.descriçao = descriçao;
	}
	public float getPreço() {
		return preço;
	}
	public void setPreço(float preço) {
		this.preço = preço;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
}