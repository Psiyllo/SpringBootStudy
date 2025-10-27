package io.github.Psyllo.libraryapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "LibraryApi",
                version = "v1",
                contact = @Contact(
                        name = "Paulo César Cabral",
                        email = "paulocesaralcabral@gmail.com",
                        url = "libraryapi.com"
                )
        )
)
public class OpenApiConfiguration {
}
