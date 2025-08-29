package io.github.Psyllo.libraryapi.service;

import io.github.Psyllo.libraryapi.model.Autor;
import io.github.Psyllo.libraryapi.model.GeneroLivro;
import io.github.Psyllo.libraryapi.model.Livro;
import io.github.Psyllo.libraryapi.repository.AutorRepository;
import io.github.Psyllo.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository.findById(UUID.fromString("075888f2-2265-43bc-9d7b-e2fe0f24b6c3")).orElse(null);
        livro.setDataPublicacao(LocalDate.of(2012,06,30));

        /**
         * Não é necessário o save pois está anotado com @Transactional.
         * Portanto o estado dentro da anotação está managed e após o fechamento caso nada dê erro, ele fará o commit para o banco automaticamente.
         * Por isso o .save não é necessário no jpa caso esteja anotado com Transactional
         */
//        livroRepository.save(livro);
    }

    @Transactional
    public void executar(){

        /**
         * Commit -> Se até o final da transação tudo ocorrer sem nenhum erro, é dado um commit nas alterações
         * Rollback -> Se durante qualquer uma das operações ocorrer erro, é dado um rollback nas alteraões
         * Isso se da pois a transactional so da o commit/rollback no final de todas as operações(no fechamento da transactional)
         */

        Autor autor = new Autor();
        autor.setNome("Wendhel1");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2001, 6, 14));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("901212-23213");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setTitulo("Livro do Wendhel");
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setDataPublicacao(LocalDate.of(2002, 8, 28));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Wendhel1")){
            throw new RuntimeException("Deu erro amigo");
        }
    }
}
