package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	public List<Restaurante> listar() {

		return restauranteRepository.findAll();
	}

	public Restaurante buscar(Long id) {

		Optional<Restaurante> restaurante = restauranteRepository.findById(id);

		return restaurante.orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Restaurante id: %d não encontrado", id)));
	}

	public Restaurante salvar(Restaurante restaurante) {

		Cozinha cozinha = cadastroCozinhaService.buscar(restaurante.getCozinha().getId());

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);

	}

	public void deletar(Long id) {

		try {
			restauranteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Id: %d - Restaurante não encontrado", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Restaurante Id: %d não pôde ser deletado, pois está em uso", id));
		}
	}
}
