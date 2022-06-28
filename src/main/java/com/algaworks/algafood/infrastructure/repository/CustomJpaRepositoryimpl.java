package com.algaworks.algafood.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.algaworks.algafood.domain.repository.CustomJpaRepository;

public class CustomJpaRepositoryimpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

	private EntityManager entityManager;
	 
	/*Generate constructors from superclass second option  */
	public CustomJpaRepositoryimpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}
	
	

	@Override
	public Optional<T> buscarPrimeiro() {
		
		var jpql = "from " + getDomainClass().getName();
		
		T entity = entityManager.createQuery(jpql, getDomainClass()).setMaxResults(1).getSingleResult(); /*limita o sql gerado limitando o resultado em apenas 1 linha, retorna so 1 com singleresult*/

		return Optional.ofNullable(entity);
	}

}
