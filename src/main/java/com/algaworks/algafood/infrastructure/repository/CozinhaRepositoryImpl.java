package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Cozinha> listar() {
		TypedQuery<Cozinha> query = entityManager.createQuery("from Cozinha", Cozinha.class);

		List<Cozinha> lista = query.getResultList();

		return lista;
	}

	@Override
	public Cozinha buscar(Long id) {

		Cozinha cozinha = entityManager.find(Cozinha.class, id);

		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return cozinha;
	}

	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {

		return entityManager.merge(cozinha);
	}

	@Transactional
	@Override
	public void deletar(Long id) {

		Cozinha cozinha = buscar(id);

		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}

		entityManager.remove(cozinha);

	}

}
