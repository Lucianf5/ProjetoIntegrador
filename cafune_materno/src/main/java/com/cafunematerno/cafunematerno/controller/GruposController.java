package com.cafunematerno.cafunematerno.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafunematerno.cafunematerno.model.Grupos;
import com.cafunematerno.cafunematerno.repository.GruposRepository;

@RestController
@RequestMapping("/grupos")
public class GruposController {
	
	@Autowired
	private GruposRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Grupos>> pegarTodesGrupos(){
		List<Grupos> listaDeGrupos = repository.findAll();
		if(listaDeGrupos.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {		
			return ResponseEntity.status(200).body(listaDeGrupos);
		}
	}
	
}
