package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Cidade> listar() {

		return cidadeRepository.findAll();
	}

	public Cidade buscar(Long id) {

		Optional<Cidade> cidade = cidadeRepository.findById(id);

		return cidade.orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Cidade id: %d não encontrada", id)));

	}

	public Cidade salvar(Cidade cidade) {

		Optional<Estado> estado = estadoRepository.findById(cidade.getEstado().getId());
		
		estado.orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Estado id: %d não encontrada", cidade.getEstado().getId())));
		
		cidade.setEstado(estado.get());

		return cidadeRepository.save(cidade);
	}

	public void deletar(Long id) {

		try {
			cidadeRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cidade id: %d está em uso", id));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade id: %d não encontrada", id));
		}

	}

}
