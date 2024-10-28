package com.tcc.cantinaDigital.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	@Column(nullable = false, unique = true)
	private String nomeUsuario;
	@Column(nullable = false)
	private String senha;
	@Column(nullable = false)
	private String email;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_papeis",
			joinColumns = @JoinColumn(name="usuario_id"),
			inverseJoinColumns = @JoinColumn(name="papel_id")
			)
	private Set<Papel> papeis = new HashSet<>();

	@ManyToOne(cascade = CascadeType.ALL)
    private Carrinho carrinho;
	
	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public Usuario() {
	}

	public Usuario(Long idUsuario, String nomeUsuario, String senha, String email, Set<Papel> papeis) {
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.email = email;
		this.papeis = papeis;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(Set<Papel> papeis) {
		this.papeis = papeis;
	}
}