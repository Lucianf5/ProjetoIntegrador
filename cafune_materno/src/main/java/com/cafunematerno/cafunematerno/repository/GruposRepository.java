package com.cafunematerno.cafunematerno.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafunematerno.cafunematerno.model.Grupos;

@Repository
public interface GruposRepository extends JpaRepository<Grupos, Long> {
	
	public Optional<Object> findByNomeGrupo(String nomeGrupo);
	
	public List<Grupos> findAllByNomeGrupoContaining(String nomeGrupo);
	
}
