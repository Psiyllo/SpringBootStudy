package io.github.Psyllo.libraryapi;

import io.github.Psyllo.libraryapi.model.Autor;
import io.github.Psyllo.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class LibraryapiApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(LibraryapiApplication.class, args);
		AutorRepository repository = context.getBean(AutorRepository.class);

		exemploSalvarregistro(repository);
	}

	public static void exemploSalvarregistro(AutorRepository autorRepository){
		Autor autor = new Autor();
		autor.setNome("Paulo");
		autor.setNacionalidade("Brasileiro");
		autor.setDataNascimento(LocalDate.of(2025, 6, 14));

		var autorSalvo = autorRepository.save(autor);
		System.out.println("Autor: " + autorSalvo);
	}
}
