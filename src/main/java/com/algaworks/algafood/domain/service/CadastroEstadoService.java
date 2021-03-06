package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> listar() {

		return estadoRepository.findAll();
	}

	public Estado buscar(Long id) {

		Optional<Estado> estado = estadoRepository.findById(id);

		return estado.orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Estado id: %d não encontrado", id)));
	}

	public Estado salvar(Estado estado) {

		return estadoRepository.save(estado);
	}

	public void deletar(Long id) {

		try {
			estadoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Estado id: %d não encontrado", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Estado id: %d está em uso", id));
		}
	}
}
