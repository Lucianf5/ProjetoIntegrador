package com.cafunematerno.cafunematerno.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_postagens")
public class Postagens {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPostagem;
	
	@NotBlank
	@Size(min = 5, max = 45, message = "Entre 5 e 45 caracteres")
	private String tituloPostagem;
	
	@NotBlank
	@Size(min = 5, max = 255, message = "Entre 10 e 255 caracteres")
	private String descricaoPostagem;
	
	@Size(min = 5, max = 255, message = "Entre 10 e 255 caracteres")
	private String localizacaoPostagem;
	
	@Size(min = 10, max = 255, message = "Entre 10 e 255 caracteres")
	private String urlAnexo;
	
	
	public Long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public String getTituloPostagem() {
		return tituloPostagem;
	}

	public void setTituloPostagem(String tituloPostagem) {
		this.tituloPostagem = tituloPostagem;
	}

	public String getDescricaoPostagem() {
		return descricaoPostagem;
	}

	public void setDescricaoPostagem(String descricaoPostagem) {
		this.descricaoPostagem = descricaoPostagem;
	}

	public String getLocalizacaoPostagem() {
		return localizacaoPostagem;
	}

	public void setLocalizacaoPostagem(String localizacaoPostagem) {
		this.localizacaoPostagem = localizacaoPostagem;
	}

	public String getUrlAnexo() {
		return urlAnexo;
	}

	public void setUrlAnexo(String urlAnexo) {
		this.urlAnexo = urlAnexo;
	}
	
}
