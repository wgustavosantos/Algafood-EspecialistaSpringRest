package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.CozinhaXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	@JacksonXmlProperty(localName = "cozinha")
	public CozinhaXmlWrapper listarXmlWrapper() {

		return new CozinhaXmlWrapper(cozinhaRepository.listar());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {

		Cozinha cozinha = cozinhaRepository.buscar(id);

		return ResponseEntity.ok(cozinha);
	}

	@PostMapping()
	public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha dataCozinha) {

		Cozinha cozinha = cadastroCozinha.salvar(dataCozinha);

		return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha dataCozinha, @PathVariable Long id) {

		Cozinha cozinha = cozinhaRepository.buscar(id);

//		cozinha.setNome(dataCozinha.getNome()); 
		BeanUtils.copyProperties(dataCozinha, cozinha, "id"); //string ignorando propriedade	
		
		cozinha = cozinhaRepository.salvar(cozinha);

		return ResponseEntity.ok(cozinha);

	}
	
	@DeleteMapping(value = "/ {id}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long id) {

		Cozinha cozinha = cozinhaRepository.buscar(id);
		
		cozinhaRepository.deletar(cozinha.getId());

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build() ;

	}

}
