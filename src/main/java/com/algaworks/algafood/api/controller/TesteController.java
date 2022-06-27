package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping(value = "/teste")
public class TesteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping(value = "/restaurante/por-nome")
	public List<Restaurante> restaurantePorNome(String nome) {

		return restauranteRepository.findByNomeContaining(nome);

	}
	
	@GetMapping(value = "/restaurante/por-nome-e-taxa")
	public List<Restaurante> restaurantePorNomeAndTaxa(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

		return restauranteRepository.findByNomeContainingAndTaxaFreteBetween(nome, taxaInicial, taxaFinal);

	}
	
	@GetMapping(value = "/restaurante/por-nome-e-cozinha")
	public List<Restaurante> restaurantePorNomeAndCozinhaId(String nome, Long cozinhaId) {

		return restauranteRepository.consultarPorNome(nome, cozinhaId);

	}

}
