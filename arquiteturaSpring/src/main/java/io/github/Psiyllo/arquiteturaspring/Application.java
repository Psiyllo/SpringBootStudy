package io.github.Psiyllo.arquiteturaspring;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);

		SpringApplicationBuilder builder =
				new SpringApplicationBuilder (Application.class);

		//The following 2 profiles are active: "producao", "homologacao"
		builder.profiles("producao", "homologacao");
		//builder.lazyInitialization(true); - Todos os meus beans serão lazy

		builder.run(args);

		//contexto da aplicação já iniciada
		builder.bannerMode(Banner.Mode.OFF);
		ConfigurableApplicationContext applicationContext = builder.context();
		//var produtoRepository = applicationContext.getBean("produtoRepository");

		ConfigurableEnvironment enviroment = applicationContext.getEnvironment();
		String applicationName = enviroment.getProperty("spring.application.name");
		System.out.println("nome da aplicação: " + applicationName);

		ExemploValue value = applicationContext.getBean(ExemploValue.class);
		value.imprimirVariavel();

		AppProperties properties = applicationContext.getBean(AppProperties.class);
		System.out.println(
						"Valor 1: " + properties.getValor1() + "\n" +
						"Valor 2: " + properties.getValor2() + "\n" +
						"Texto da variável: " + properties.getVariavel()
		);
	}
}
