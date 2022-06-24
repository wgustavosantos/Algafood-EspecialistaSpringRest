package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "estados")
public class EstadoController {

	@Autowired
	private CadastroEstadoService cadastroEstadoService;

	@GetMapping
	public ResponseEntity<List<Estado>> estados() {

		List<Estado> estados = cadastroEstadoService.listar();

		return ResponseEntity.ok(estados);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Estado> buscar(@PathVariable Long id) {

		Estado estado;

		try {
			estado = cadastroEstadoService.buscar(id);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(estado);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {

		try {
			cadastroEstadoService.deletar(id);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}

		return ResponseEntity.noContent().build();

	}

	@PostMapping
	public ResponseEntity<Estado> salvar(@RequestBody Estado estado) {

		estado = cadastroEstadoService.salvar(estado);

		return ResponseEntity.status(HttpStatus.CREATED).body(estado);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Estado estadoNovo, @PathVariable Long id) {

		Estado estado;
		try {
			estado = cadastroEstadoService.buscar(id);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		BeanUtils.copyProperties(estadoNovo, estado, "id");
		
		estado = cadastroEstadoService.salvar(estado);

		return ResponseEntity.status(HttpStatus.CREATED).body(estado);

	}

}