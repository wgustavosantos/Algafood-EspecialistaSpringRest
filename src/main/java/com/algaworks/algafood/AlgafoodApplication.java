package com.algaworks.algafood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.algaworks.algafood.infrastructure.repository.CustomJpaRepositoryimpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryimpl.class)
public class AlgafoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApplication.class, args);
	}

}
