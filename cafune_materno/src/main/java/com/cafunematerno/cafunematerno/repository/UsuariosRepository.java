package com.cafunematerno.cafunematerno.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafunematerno.cafunematerno.model.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
	
	public Optional<Object> findByNomeCompleto(String nomeCompleto);
	
	public List<Object> findAllByNomeCompletoContaining(String nomeCompleto);
	
	public Optional<Usuarios> findByEmailIgnoreCase(String email);
	
	public List<Usuarios> findByIdUsuario(Long idUsuario);
	
}
