package io.github.Psiyllo.arquiteturaspring.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository repository;

    public TodoService(TodoRepository todoRepository){
        this.repository = todoRepository;
    }

    public TodoEntity salvar (TodoEntity novoTodo){
        return repository.save(novoTodo);
    }

    public void atualizar (TodoEntity todo){
        repository.save(todo);
    }

    public TodoEntity buscar (Integer id){
        return repository.findById(id).orElse(null);
    }

}
