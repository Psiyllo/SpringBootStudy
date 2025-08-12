package io.github.Psiyllo.arquiteturaspring.montadora.configuration;

import io.github.Psiyllo.arquiteturaspring.montadora.Motor;
import io.github.Psiyllo.arquiteturaspring.montadora.TipoMotor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MontadoraConfiguration {

    @Bean(name = "motorAspirado")
    @Primary //(anotação para ser o padrão utilizado caso nao tenha definido o qualifier de qual sera injetado)
    //name = nesse caso é apenas demonstrativo
    public Motor motorAspirado(@Value("${app.montadora.motor-padrao}") Integer cavalos){
        var motor = new Motor();
        motor.setCavalos(cavalos);
        motor.setCilindros(4);
        motor.setLitragem(2.0);
        motor.setTipo(TipoMotor.ASPIRADO);
        motor.setModelo("XPTO-0");
        return motor;
    }

    @Bean(name = "motorEletrico")
    //name = nesse caso é apenas demonstrativo
    public Motor motorEletrico(){
        var motor = new Motor();
        motor.setCavalos(110);
        motor.setCilindros(3);
        motor.setLitragem(1.4);
        motor.setTipo(TipoMotor.ELETRICO);
        motor.setModelo("TH-40");
        return motor;
    }

    @Bean(name = "motorTurbo")
    //name = nesse caso é apenas demonstrativo
    public Motor motorTurbo(){
        var motor = new Motor();
        motor.setCavalos(180);
        motor.setCilindros(4);
        motor.setLitragem(1.5);
        motor.setTipo(TipoMotor.TURBO);
        motor.setModelo("XPTO-01");
        return motor;
    }
}
