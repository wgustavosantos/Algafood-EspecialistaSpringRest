package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;

/*
 * Sufixo Impl para o Sring identificar 
 * implementação customizada de um repositrio
 */

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder(); 
		
		CriteriaQuery<Restaurante> criteria = criteriaBuilder.createQuery(Restaurante.class);
		
		Root<Restaurante> root = criteria.from(Restaurante.class); // from Restaurante, o root é o Restaurante
		
		Predicate nomePredicate = criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
		
		Predicate taxaInicialPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);
		Predicate taxaFinalPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);
		
		criteria.where(nomePredicate, taxaInicialPredicate, taxaFinalPredicate);
		
		TypedQuery<Restaurante> query = entityManager.createQuery(criteria);

		return query.getResultList();
	}
}
