package io.github.Psiyllo.arquiteturaspring.todos;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

    private TodoRepository repository;

    public MailSender(TodoRepository repository) {
        this.repository = repository;
    }

    public MailSender() {

    }

    public void enviar (String mensagem){
        System.out.println("Enviando email: " + mensagem);

    }

}
