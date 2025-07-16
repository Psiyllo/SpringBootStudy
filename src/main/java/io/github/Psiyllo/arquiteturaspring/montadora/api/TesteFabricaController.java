package io.github.Psiyllo.arquiteturaspring.montadora.api;

import io.github.Psiyllo.arquiteturaspring.montadora.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carros")
public class TesteFabricaController {

    @Autowired
//    @Qualifier("motorEletrico")
    // Tirei o Qualifier, Após isso da erro pois não tem o motor para ser utilizado.
    // O problema pode ser resolvido com @Primary na configuration, dessa forma o motor que estiver com a anotação será utilizado caso não tenha o qualifier no controller.
    @Turbo
    private Motor motor;

    @PostMapping
    public CarroStatus ligarCarro(@RequestBody Chave chave){
        var carro = new HondaHRV(motor);
        return carro.darIgnicao(chave);
    }

}
