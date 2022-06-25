package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;

public interface CozinhaRepository {

	public List<Cozinha> listar();
	
	List<Cozinha> consultarPorNome(String nome);

	public Cozinha buscar(Long id);

	public Cozinha salvar(Cozinha cozinha);

	public void deletar(Long id);
}
