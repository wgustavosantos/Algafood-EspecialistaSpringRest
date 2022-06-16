package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Restaurante> listar() {
		TypedQuery<Restaurante> query = entityManager.createQuery("from Restaurante", Restaurante.class);

		List<Restaurante> lista = query.getResultList();

		return lista;
	}

	@Override
	public Restaurante buscar(Long id) {
		return entityManager.find(Restaurante.class, id);
	}

	@Transactional
	@Override
	public Restaurante salvar(Restaurante cozinha) {
		
		return entityManager.merge(cozinha);
	}

	@Transactional
	@Override
	public void deletar(Long id) {

		Restaurante cozinha = buscar(id);

		entityManager.remove(cozinha);
		
	}

}
