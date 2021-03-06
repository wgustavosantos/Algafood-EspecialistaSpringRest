package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs;

@RestController
@RequestMapping(value = "/teste")
public class TesteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping(value = "/restaurante/por-nome")
	public List<Restaurante> restaurantePorNome(String nome) {

		return restauranteRepository.findByNomeContaining(nome);

	}

//	@GetMapping(value = "/restaurante/por-nome-e-taxa")
//	public List<Restaurante> restaurantePorNomeAndTaxa(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
//
//		return restauranteRepository.findByNomeContainingAndTaxaFreteBetween(nome, taxaInicial, taxaFinal);
//
//	}

	@GetMapping(value = "/restaurante/por-nome-e-cozinha")
	public List<Restaurante> restaurantePorNomeAndCozinhaId(String nome, Long cozinhaId) {

		return restauranteRepository.consultarPorNome(nome, cozinhaId);

	}

	@GetMapping(value = "/restaurante/por-nome-e-taxa")
	public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

		return restauranteRepository.find(nome, taxaInicial, taxaFinal);

	}

	@GetMapping(value = "/restaurante/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

		return restauranteRepository
				.findAll(RestauranteSpecs.comFreteGratis()
						.and(RestauranteSpecs.comNomeSemelhante(nome)));

	}
	
	@GetMapping(value = "/restaurante/primeiro")
	public Optional<Restaurante> restaurantePrimeiro() {

		return restauranteRepository.buscarPrimeiro();

	}
	
	@GetMapping(value = "/cozinha/primeiro")
	public Optional<Cozinha> cozinhaPrimeiro() {

		return cozinhaRepository.buscarPrimeiro();

	}

}
