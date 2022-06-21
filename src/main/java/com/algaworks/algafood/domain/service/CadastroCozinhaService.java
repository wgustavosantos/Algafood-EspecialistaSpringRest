package com.algaworks.algafood.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	List<String> palavras = new ArrayList<>();

	public Cozinha salvar(Cozinha cozinha) {

		return cozinhaRepository.salvar(cozinha);
	}

	public void excluir(Long id) {

		try {
			cozinhaRepository.deletar(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Id: %d - Cozinha não encontrada", id));
		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
		}
	}

	public List<Cozinha> listar() {

		return cozinhaRepository.listar();
	}

	public Cozinha buscar(Long id) {

		Cozinha cozinha;

		try {
			cozinha = cozinhaRepository.buscar(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Id: %d - Coziznha não encontrada", id));
		}
		return cozinha;
	}

}
