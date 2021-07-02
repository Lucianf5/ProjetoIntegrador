package com.cafunematerno.cafunematerno.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cafunematerno.cafunematerno.model.Grupos;
import com.cafunematerno.cafunematerno.model.Usuarios;

@Repository
public interface GruposRepository extends JpaRepository<Grupos, Long> {
	
	public Optional<Object> findByNomeGrupo(String nomeGrupo);
	
	public List<Grupos> findAllByNomeGrupoContaining(String nomeGrupo);
	
	//@Query(value= "SELECT * FROM db_cafune_materno.tb_grupos WHERE id_grupo = :id", nativeQuery = true)
	//public List<Grupos> pegarOqueEuQuiser(@Param("id") Long id);
}
