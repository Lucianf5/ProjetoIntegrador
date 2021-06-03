package com.cafunematerno.cafunematerno.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cafunematerno.cafunematerno.model.Usuarios;
import com.cafunematerno.cafunematerno.repository.UsuariosRepository;

@Service
public class UsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepository;

	/**
	 * Método utilizado para acessar a lista de usuários no BD.
	 * 
	 * @return ResponseEntity com status No Content caso a lista esteja vazia ou
	 *         ResponseEntity com status Ok, caso existam usuários nesta lista.
	 * @since 1.0
	 * @author Grupo: Angelo, Ellen, Julio, Luciano e Nathalia.
	 */
	public ResponseEntity<List<Usuarios>> findAll() {
		List<Usuarios> listaDeUsuarios = usuariosRepository.findAll();
		if (listaDeUsuarios.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaDeUsuarios);
		}
	}

	/**
	 * Método utilizado para acessar um usuário no DB a partir de seu Id.
	 * 
	 * @return ResponseEntity com status No Content caso não encontre um Id no BD
	 *         esteja vazia ou ResponseEntity com status Ok, caso exista o Id no BD.
	 * @since 1.0
	 * @author Grupo: Angelo, Ellen, Julio, Luciano e Nathalia.
	 */
	public ResponseEntity<Usuarios> procurarIdUsuario(Long idUsuario) {
		return usuariosRepository.findById(idUsuario)
				.map(existeIdUsuario -> ResponseEntity.status(200).body(existeIdUsuario))
				.orElse(ResponseEntity.status(204).build());
	}

	public ResponseEntity<Object> procurarNomeUsuario(String nomeCompleto){
		List<Object> listaUsuarios = usuariosRepository.findAllByNomeCompletoContaining(nomeCompleto);
		if (listaUsuarios.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaUsuarios);
		}
	}
	
	/**
	 * Método utilizado para cadastrar um novo usuário no sistema, validando sua
	 * existência a partir do e-mail.
	 * 
	 * @param novoUsuario
	 * @return ResponseEntity com status Created com a entidade Usuario ou
	 *         ResponseEntity com status Not Acceptable, caso esse usuário já
	 *         exista.
	 * @since 1.1
	 * @author Grupo: Angelo, Ellen, Julio, Luciano e Nathalia.
	 */
	public ResponseEntity<Usuarios> cadastrarNovoUsuario(@Valid Usuarios novoUsuario) {
		Optional<Object> verificarUsuario = usuariosRepository.findByEmail(novoUsuario.getEmail());

		if (verificarUsuario.isPresent()) {
			return ResponseEntity.status(406).build();
		} else {
			return ResponseEntity.status(201).body(usuariosRepository.save(novoUsuario));
		}
	}

	/**
	 * Método utilizado para verificar se existe o Id do usuário no BD e se este
	 * e-mail já está sendo utilizado.
	 * 
	 * @return ResponseEntity com status Not Modified caso não encontre um Id no BD
	 *         ou caso exista o e-mal, ou ResponseEntity com status Accepted,
	 *         alterando as informações do Usuário selecionado no BD.
	 * @since 1.0
	 * @author Grupo: Angelo, Ellen, Julio, Luciano e Nathalia.
	 */
	public ResponseEntity<Usuarios> atualizarUsuario(Long idUsuario, Usuarios usuarioAtualizado) {
		Optional<Usuarios> idUsuarioJaExiste = usuariosRepository.findById(idUsuario);
		Optional<Object> emailUsuarioJaExiste = usuariosRepository.findByEmail(usuarioAtualizado.getEmail());

		if (idUsuarioJaExiste.isPresent() && emailUsuarioJaExiste.isEmpty()) {

			if (usuarioAtualizado.getNomeCompleto() == null) {
				idUsuarioJaExiste.get().setSenha(usuarioAtualizado.getSenha());
				return ResponseEntity.status(202).body(usuariosRepository.save(idUsuarioJaExiste.get()));

			} else if (usuarioAtualizado.getSenha() == null) {
				idUsuarioJaExiste.get().setNomeCompleto(usuarioAtualizado.getNomeCompleto());
				return ResponseEntity.status(202).body(usuariosRepository.save(idUsuarioJaExiste.get()));

			} else {
				idUsuarioJaExiste.get().setNomeCompleto(usuarioAtualizado.getNomeCompleto());
				idUsuarioJaExiste.get().setSenha(usuarioAtualizado.getSenha());
				return ResponseEntity.status(202).body(usuariosRepository.save(idUsuarioJaExiste.get()));
			}
		} else {
			return ResponseEntity.status(304).build();
		}
	}

	/**
	 * Método utilizado para verificar se existe o Id do usuário no BD e deleta-lo.
	 * 
	 * @return ResponseEntity com status Bad Request caso não encontre um Id no BD ,
	 *         ou ResponseEntity com status Ok, deletando o Usuário selecionado do
	 *         BD.
	 * @since 1.0
	 * @author Grupo: Angelo, Ellen, Julio, Luciano e Nathalia.
	 */
	public ResponseEntity<Object> deletarIdUsuario(Long idUsuario) {
		Optional<Usuarios> idUsuarioExistente = usuariosRepository.findById(idUsuario);

		if (idUsuarioExistente.isEmpty()) {
			return ResponseEntity.status(400).body("Usuário não localizado. Por favor tente outro.");
		} else {
			usuariosRepository.deleteById(idUsuario);
			return ResponseEntity.status(200).body("Usuário deletado com sucesso!");
		}
	}

}
