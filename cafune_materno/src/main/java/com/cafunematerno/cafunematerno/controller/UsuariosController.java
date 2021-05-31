package com.cafunematerno.cafunematerno.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafunematerno.cafunematerno.model.Usuarios;
import com.cafunematerno.cafunematerno.repository.UsuariosRepository;
import com.cafunematerno.cafunematerno.service.UsuariosService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private UsuariosRepository repository;
	
	@Autowired
	private UsuariosService service;
	
	@GetMapping
	public ResponseEntity<List<Usuarios>> pegarTodesUsuarios(){
		List<Usuarios> listaDeUsuarios = repository.findAll();
		if(listaDeUsuarios.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {		
			return ResponseEntity.status(200).body(listaDeUsuarios);
		}
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Object> salvarNovoUsuario(@Valid @RequestBody Usuarios novoUsuario) {
		return service.cadastrarUsuario(novoUsuario)
				.map(adicionarUsuario -> ResponseEntity.status(201).body(adicionarUsuario))
				.orElse(ResponseEntity.status(400).body("E-mail já cadastrado. Por favor, insira outro."));
	}
}
