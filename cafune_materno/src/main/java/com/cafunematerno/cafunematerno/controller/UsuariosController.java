package com.cafunematerno.cafunematerno.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.cafunematerno.cafunematerno.model.UserLogin;
import com.cafunematerno.cafunematerno.model.Usuarios;
import com.cafunematerno.cafunematerno.service.UsuariosService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuariosController {
	
	@Autowired
	private UsuariosService serviceUsuarios;
	
	
	@GetMapping
	public ResponseEntity<List<Usuarios>> getAll() {
		return serviceUsuarios.findAll();
	}
	
	@GetMapping("/id/{id_usuario}")
	public ResponseEntity<Usuarios> buscarUsuarioPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
		return serviceUsuarios.procurarIdUsuario(idUsuario);
	}	
	
	@GetMapping("/nome/{nome_completo}")
	public ResponseEntity<Object> buscarUsuarioPorNomeCompleto(@PathVariable(value="nome_completo") String nomeCompleto){
		return serviceUsuarios.procurarNomeUsuario(nomeCompleto);
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuarios> salvarNovoUsuario(@RequestBody Usuarios novoUsuario) {
			return serviceUsuarios.cadastrarNovoUsuario(novoUsuario);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuarios> alterarUsuario(@Valid @RequestBody Usuarios usuarioParaAtualiza) {
			return serviceUsuarios.atualizarUsuario(usuarioParaAtualiza);
	}
	
	@DeleteMapping("/deletar")
	public ResponseEntity<Object> deletarUsuarioAtravesDoId(@RequestParam Long idUsuario) {
		return serviceUsuarios.deletarIdUsuario(idUsuario);
	}
	
	@PostMapping("/logar")
	public ResponseEntity<Object> logarUsuario(@RequestBody UserLogin user) {
		return serviceUsuarios.logarUsuario(user);
	}
}





