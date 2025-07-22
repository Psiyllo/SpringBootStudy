package io.github.Psiyllo.arquiteturaspring;

import io.github.Psiyllo.arquiteturaspring.todos.*;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionCreator;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;

public class ExemploInjecaoDependencia {
    public static void main(String[] args) throws Exception  {

         DriverManagerDataSource dataSource = new DriverManagerDataSource();
         dataSource.setUrl("url");
         dataSource.setUsername("user");
         dataSource.setPassword("password");

         Connection connection= dataSource.getConnection();

         EntityManager entityManager = null;

         TodoRepository repository = new SimpleJpaRepository<TodoEntity, Integer>();
         TodoValidator validator = new TodoValidator(repository);
         MailSender sender = new MailSender();

         TodoService todoService = new TodoService(repository, validator, sender);

    }
}
