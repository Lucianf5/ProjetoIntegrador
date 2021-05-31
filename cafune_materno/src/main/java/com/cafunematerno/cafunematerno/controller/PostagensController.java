package com.cafunematerno.cafunematerno.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafunematerno.cafunematerno.model.Postagens;
import com.cafunematerno.cafunematerno.repository.PostagensRepository;

@RestController
@RequestMapping("/postagens")
public class PostagensController {
	
	@Autowired
	private PostagensRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Postagens>> pegarTodesPostagens(){
		List<Postagens> listaDePostagens = repository.findAll();
		if(listaDePostagens.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {		
			return ResponseEntity.status(200).body(listaDePostagens);
		}
	}
}
