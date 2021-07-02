package com.cafunematerno.cafunematerno.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafunematerno.cafunematerno.model.Grupos;
import com.cafunematerno.cafunematerno.model.Usuarios;
import com.cafunematerno.cafunematerno.service.GruposService;

@RestController
@RequestMapping("/grupos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GruposController {
	
	@Autowired
	private GruposService serviceGrupos;
	

	@GetMapping
	public ResponseEntity<List<Grupos>> getAll(){
		return serviceGrupos.pegarTodosGrupos();
	}
	
	@GetMapping("/id/{id_grupo}")
	public ResponseEntity<Grupos> buscarGrupoPorId(@PathVariable(value = "id_grupo") Long idGrupo) {
		return serviceGrupos.procurarIdGrupos(idGrupo);
	}
	
	@PostMapping("/salvar/usuario/{id_usuario}")
	public ResponseEntity<Usuarios> salvarNovoGrupo(@PathVariable(value = "id_usuario") Long idUsuario, @RequestBody Grupos novoGrupo) {
		return serviceGrupos.salvarGrupos(idUsuario, novoGrupo);
	}
	
	@PutMapping("/atualizar/{id_grupo}")
	public ResponseEntity<Grupos> alterarGrupo(@PathVariable (value = "id_grupo") Long idGrupo, @RequestBody Grupos grupoAtualizado) {
		return serviceGrupos.atualizarGrupo(idGrupo, grupoAtualizado);
	}
	
	@DeleteMapping("/deletar")
	public ResponseEntity<String> deletarGrupoAtravesDoId(@RequestParam Long idGrupo) {
		return serviceGrupos.deletarIdGrupo(idGrupo);
	}
}
