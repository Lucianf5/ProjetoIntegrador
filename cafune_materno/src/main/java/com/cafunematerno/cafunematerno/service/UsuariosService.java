package com.cafunematerno.cafunematerno.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafunematerno.cafunematerno.model.Usuarios;
import com.cafunematerno.cafunematerno.repository.UsuariosRepository;

@Service
public class UsuariosService {
	
	@Autowired
	private UsuariosRepository repository;
	
	/**
	 * Método utilizado para cadastrar um novo usuário no sistema, validando sua existência a partir do e-mail.
	 * @param novoUsuario
	 * @return Optional com a entidade do Usuario ou Optional vazio, caso esse usuário já exista.
	 * @since 1.0
	 * @author Grupo Angelo, Ellen, Julio, Luciano e Nathalia.  
	 */
	
	public Optional<Object> cadastrarUsuario(Usuarios novoUsuario){
		Optional<Object> usuarioExiste = repository.findByEmail(novoUsuario.getEmail());
		
		if(usuarioExiste.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.ofNullable(repository.save(novoUsuario));
		}
	}
	
}
