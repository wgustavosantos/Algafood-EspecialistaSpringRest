package com.algaworks.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CadastroCozinha {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Cozinha> listar() {

		TypedQuery<Cozinha> query = entityManager.createQuery("from Cozinha", Cozinha.class);

		List<Cozinha> lista = query.getResultList();

		return lista;
	}

}
