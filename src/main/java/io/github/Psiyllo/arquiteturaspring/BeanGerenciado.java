package io.github.Psiyllo.arquiteturaspring;

import io.github.Psiyllo.arquiteturaspring.todos.TodoEntity;
import io.github.Psiyllo.arquiteturaspring.todos.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

//@Lazy(Value = true) - Comportamento lazy serve para que os beans sejam instanciados apenas quando for utilizados.
@Component
//@Scope(BeanDefinition.SCOPE_SINGLETON) - Padrão, cria apenas uma instância.
//@Scope(WebApplicationContext.SCOPE_REQUEST) - Bom para desenvolvimento web, cria uma instância para cada requisição HTTP
//@Scope(WebApplicationContext.SCOPE_SESSION) - Uma instância é criada para cada sessão HTTP do utilizador.
//@Scope(WebApplicationContext.SCOPE_APPLICATION) - Aplicações web, serve para todos os usuários, Session (um usuario), Aplication (todos usuarios)
public class BeanGerenciado {

    @Autowired
    private TodoValidator validator;

    //Fazer injeção de dependência via construtor é o padrão mais adequado e recomendado pelo spring.
    public BeanGerenciado(TodoValidator validator) {
        this.validator = validator;
    }

    public void utilizar(){
        var todo = new TodoEntity();
        validator.validar(todo);
    }

    @Autowired
    public void setValidator(TodoValidator validator){
        this.validator = validator;
    }
}
