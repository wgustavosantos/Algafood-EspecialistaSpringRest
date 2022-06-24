package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Cidade> listar() {
		TypedQuery<Cidade> query = entityManager.createQuery("from Cidade", Cidade.class);

		List<Cidade> lista = query.getResultList();

		return lista;
	}

	@Override
	public Cidade buscar(Long id) {

		Cidade cidade = entityManager.find(Cidade.class, id);

		if (cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return cidade;
	}

	@Transactional
	@Override
	public Cidade salvar(Cidade cidade) {

		return entityManager.merge(cidade);
	}

	@Transactional
	@Override
	public void deletar(Long id) {

		Cidade cidade = buscar(id);
		
		if(cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}
		entityManager.remove(cidade);

	}
}
