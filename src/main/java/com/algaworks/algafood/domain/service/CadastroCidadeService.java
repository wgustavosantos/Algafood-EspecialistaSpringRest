package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	public List<Cidade> listar() {

		return cidadeRepository.listar();
	}

	public Cidade buscar(Long id) {

		Cidade cidade;

		try {
			cidade = cidadeRepository.buscar(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade id: %d não encontrada", id));
		}

		return cidade;
	}

	public void deletar(Long id) {

		try {
			cidadeRepository.deletar(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cidade Id: %d não pôde ser deletada, pois está em uso", id));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Cidade Id: %d não pôde ser deletado, id não encontrado", id));
		}

	}

	public Cidade salvar(Cidade cidade) {

		return cidadeRepository.salvar(cidade);
	}

}
