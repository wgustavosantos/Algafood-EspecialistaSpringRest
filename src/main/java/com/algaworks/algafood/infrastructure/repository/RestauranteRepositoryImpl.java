package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
		
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasText(nome)) {
			Predicate nomePredicate = criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
			predicates.add(nomePredicate);
		}
		
		if(taxaFreteInicial != null) {
			Predicate taxaInicialPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"),
					taxaFreteInicial);
			predicates.add(taxaInicialPredicate);
		}
		
		if(taxaFreteFinal != null) {
			Predicate taxaFinalPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);
			predicates.add(taxaFinalPredicate);
		}
		
		
		
		criteria.where(predicates.toArray(new Predicate[0])); // separa por and // converte um arrayList para um List
		
		TypedQuery<Restaurante> query = entityManager.createQuery(criteria);

		return query.getResultList();
	}
}
