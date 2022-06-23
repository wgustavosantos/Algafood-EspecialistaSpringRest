package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private CadastroRestauranteService restauranteService;

	@GetMapping
	public List<Restaurante> listar() {

		return restauranteService.listar();

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {

		Restaurante restaurante;

		try {
			restaurante = restauranteService.buscar(id);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(restaurante);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Restaurante> deletar(@PathVariable Long id) {

		try {
			restauranteService.deletar(id);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		return ResponseEntity.noContent().build();

	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {

		try {
			restaurante = restauranteService.salvar(restaurante);

			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Restaurante restaurante, @PathVariable Long id) {

		try {
			restauranteService.buscar(id);
			restaurante.setId(id);
			restaurante = restauranteService.salvar(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.ok(restaurante);

	}

}
