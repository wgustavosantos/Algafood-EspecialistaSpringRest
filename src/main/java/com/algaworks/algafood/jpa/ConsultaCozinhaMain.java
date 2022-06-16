package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.infrastructure.repository.CozinhaRepositoryImpl;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
				.web(WebApplicationType.NONE).run(args);

		CozinhaRepositoryImpl repository = applicationContext.getBean(CozinhaRepositoryImpl.class);
		
		List<Cozinha> cozinhas = repository.listar();

		for (Cozinha cozinha : cozinhas) {
			System.out.println("Nome da cozinha: " + cozinha.getNome());
		}
	}

}
