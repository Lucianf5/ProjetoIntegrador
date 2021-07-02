package com.cafunematerno.cafunematerno.model;

import javax.validation.constraints.NotBlank;

public class UserLogin {
	
	private Long idUserLogin;
	
	private String nome;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
	
	private String token;
	
	private String foto;
	
	private String tipo;
	
	public UserLogin() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getIdUserLogin() {
		return idUserLogin;
	}

	public void setIdUserLogin(Long idUserLogin) {
		this.idUserLogin = idUserLogin;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
