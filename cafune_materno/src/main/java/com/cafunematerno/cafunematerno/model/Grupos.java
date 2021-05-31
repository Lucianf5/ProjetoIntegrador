package com.cafunematerno.cafunematerno.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="tb_grupos")
public class Grupos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGrupo;
	
	@NotNull
	@Size(min = 5, max = 45, message = "Entre 5 e 45 caracteres")
	private String nomeGrupo;
	
	@NotNull
	@Size(min = 5, max = 45, message = "Entre 5 e 45 caracteres")
	private String tema;
	
	@NotNull
	@Size(max = 45, message = "Máximo de 45 participantes.")
	private int qntUsuarios;
	
	
	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public int getQntUsuarios() {
		return qntUsuarios;
	}

	public void setQntUsuarios(int qntUsuarios) {
		this.qntUsuarios = qntUsuarios;
	}
	
}
