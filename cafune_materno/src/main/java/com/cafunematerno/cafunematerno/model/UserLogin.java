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
	
	private String status;
	
	private String sobre;
	
	private String localizacao;
	
	private String pronome;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getPronome() {
		return pronome;
	}

	public void setPronome(String pronome) {
		this.pronome = pronome;
	}
	
	
}
