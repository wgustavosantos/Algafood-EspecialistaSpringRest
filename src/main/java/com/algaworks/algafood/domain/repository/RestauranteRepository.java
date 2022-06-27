package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries {

	List<Restaurante> findByNomeContaining(String nome);

	List<Restaurante> findByNomeContainingAndTaxaFreteBetween(String nome, BigDecimal taxaInicial,
			BigDecimal taxaFinal);

	// List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long
	// cozinhaId);

	@Query(value = "from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);


}
