package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.infrastructure.repository.RestauranteRepositoryImpl;

public class BuscarRestaurante {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
				.web(WebApplicationType.NONE).run(args);

		RestauranteRepositoryImpl repository = applicationContext.getBean(RestauranteRepositoryImpl.class);
		
		List<Restaurante> restaurantes = repository.listar();
		
		for (Restaurante res : restaurantes) {
			System.out.printf("%s - %.2f - %s", res.getNome(), res.getTaxaFrete(), res.getCozinha().getNome());
		}
	}

}
