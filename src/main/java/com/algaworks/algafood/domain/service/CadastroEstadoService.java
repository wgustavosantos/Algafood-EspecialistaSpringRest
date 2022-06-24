package com.algaworks.algafood.domain.service;

import java.util.List;

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

		return estadoRepository.listar();
	}

	public Estado buscar(Long id) {

		Estado estado;

		try {
			estado = estadoRepository.buscar(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Id: %d - Estado não encontrado", id));
		}
		return estado;
	}

	public void deletar(Long id) {

		@SuppressWarnings("unused")
		Estado estado = null;
		try {
			estado = this.buscar(id);

			estadoRepository.deletar(id);

		} catch (EntidadeNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removido, pois está em uso", id));
		}

	}

	public Estado salvar(Estado estado) {

		return estadoRepository.salvar(estado);
	}

}
