package com.cafunematerno.cafunematerno.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafunematerno.cafunematerno.model.Postagens;

@Repository
public interface PostagensRepository extends JpaRepository<Postagens, Long> {
	
	public Optional<Object> findByTituloPostagem(String tituloPostagem);
	
	public List<Postagens> findAllByTituloPostagemContaining(String tituloPostagem);
	
	public List<Postagens> findAllByDescricaoPostagemContaining(String descricaoPostagem);
	
}
