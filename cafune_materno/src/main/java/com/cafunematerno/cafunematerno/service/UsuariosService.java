package com.cafunematerno.cafunematerno.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafunematerno.cafunematerno.model.UserLogin;
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
		List<Usuarios> listaDeUsuarios = usuariosRepository.findAll().stream().map(usuario -> {
			usuario.setSenha("");
			return usuario;
		}).collect(Collectors.toList());
		if (listaDeUsuarios.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaDeUsuarios);
		}
	}

	/**
	 * Método utilizado para acessar um usuário no BD a partir de seu Id.
	 * 
	 * @return ResponseEntity com status No Content caso não encontre um Id no BD
	 *         esteja vazia ou ResponseEntity com status Ok, caso exista o Id no BD.
	 * @since 1.0
	 * @author Grupo: Angelo, Ellen, Julio, Luciano e Nathalia.
	 */
	public ResponseEntity<Usuarios> procurarIdUsuario(Long idUsuario) {
		return usuariosRepository.findById(idUsuario)
				.map(existeIdUsuario -> {
					existeIdUsuario.setSenha("");
					return ResponseEntity.status(200).body(existeIdUsuario);
				})
				.orElse(ResponseEntity.status(204).build());
	}

	/**
	 * Método utilizado para acessar um usuário no BD a partir de seu Nome Completo.
	 * 
	 * @param nomeCompleto
	 * @return ResponseEntity com Status No Content caso não encontre o Nome
	 *         Completo no BD e esteja vazia ou ResponseEntity com status Ok, caso
	 *         exista o Nome Completo no BD.
	 * 
	 * @since 1.0
	 * @author Grupo: Angelo, Ellen, Julio, Luciano e Nathalia.
	 */
	public ResponseEntity<Object> procurarNomeUsuario(String nomeCompleto) {
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
		Optional<Usuarios> verificarUsuario = usuariosRepository.findByEmailIgnoreCase(novoUsuario.getEmail());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (verificarUsuario.isPresent()) {
			return ResponseEntity.status(406).build();
		} else {
			String senhaEncoder = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(senhaEncoder);
			return ResponseEntity.status(201).body(usuariosRepository.save(novoUsuario));
		}
	}

	/**
	 * Método utilizado atualizar as informações do usuário, validando sua
	 * existência a partir do e-mail
	 * 
	 * @return ResponseEntity com status Not Modified caso não encontre o e-mail no
	 *         BD ou ResponseEntity com status Accepted, alterando as informações do
	 *         Usuário selecionado no BD.
	 * @since 2.0
	 * @author Grupo: Angelo, Ellen, Julio, Luciano e Nathalia.
	 */

	public ResponseEntity<Usuarios> atualizarUsuario(Usuarios usuarioParaAtualizar) {
		return usuariosRepository.findById(usuarioParaAtualizar.getIdUsuario()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCodificada = encoder.encode(usuarioParaAtualizar.getSenha());
			usuarioExistente.setNomeCompleto(usuarioParaAtualizar.getNomeCompleto());
			usuarioExistente.setSenha(senhaCodificada);
			usuarioExistente.setFoto(usuarioParaAtualizar.getFoto());
			usuarioExistente.setStatus(usuarioParaAtualizar.getStatus());
			usuarioExistente.setPronome(usuarioParaAtualizar.getPronome());
			usuarioExistente.setLocalizacao(usuarioParaAtualizar.getLocalizacao());
			usuarioExistente.setSobre(usuarioParaAtualizar.getSobre());
			
			return ResponseEntity.status(202).body(usuariosRepository.save(usuarioExistente));
		}).orElse(ResponseEntity.status(401).build());

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

	public ResponseEntity<Object> logarUsuario(UserLogin user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuarios> usuarioExistente = usuariosRepository.findByEmailIgnoreCase(user.getEmail());
		
		
		if (usuarioExistente.isPresent() && encoder.matches(user.getSenha(), usuarioExistente.get().getSenha())) {
			String auth = user.getEmail() + ":" + user.getSenha();

			byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
			String authHeader = "Basic " + new String(encodeAuth);

			user.setToken(authHeader);
			user.setEmail(usuarioExistente.get().getEmail());
			user.setNome(usuarioExistente.get().getNomeCompleto());
			user.setIdUserLogin(usuarioExistente.get().getIdUsuario());
			user.setFoto(usuarioExistente.get().getFoto());
			user.setTipo(usuarioExistente.get().getTipo());
			user.setSenha("");

			return ResponseEntity.status(200).body(user);

		} else {
			return ResponseEntity.status(401).build();
		}
	}

}
