package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.infrastructure.repository.CozinhaRepositoryImpl;

public class AdicionarCozinhaMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
				.web(WebApplicationType.NONE).run(args);

		CozinhaRepositoryImpl repository = applicationContext.getBean(CozinhaRepositoryImpl.class);

		Cozinha cozinha1 = new Cozinha(null, "Brasileira");
		Cozinha cozinha2 = new Cozinha(null, "Japonesa");
		
		cozinha1 = repository.salvar(cozinha1);
		cozinha2 = repository.salvar(cozinha2);
		
		System.out.printf("id: %d - Nome: %s\n", cozinha1.getId(), cozinha1.getNome());
		System.out.printf("id: %d - Nome: %s\n", cozinha2.getId(), cozinha2.getNome());
		
		
		
	}

}
