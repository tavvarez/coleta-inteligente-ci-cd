package br.com.gestao_residuos_automacao;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Gestão de Resíduos API", version = "1.0", description = "API para gestão de agendamentos de coleta de resíduos"))
public class GestaoResiduosAutomacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoResiduosAutomacaoApplication.class, args);
    }
}
