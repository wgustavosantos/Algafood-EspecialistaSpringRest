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
	public ResponseEntity<?> buscar(@PathVariable Long id) {

		try {
			Estado estado = cadastroEstadoService.buscar(id);
			return ResponseEntity.ok(estado);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {

		try {
			cadastroEstadoService.deletar(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<Estado> salvar(@RequestBody Estado estado) {

		estado = cadastroEstadoService.salvar(estado);

		return ResponseEntity.status(HttpStatus.CREATED).body(estado);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Estado estadoNovo, @PathVariable Long id) {

		try {
			
			Estado estado = cadastroEstadoService.buscar(id);
			BeanUtils.copyProperties(estadoNovo, estado, "id");
			estado = cadastroEstadoService.salvar(estado);
			
			return ResponseEntity.status(HttpStatus.OK).body(estado);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}