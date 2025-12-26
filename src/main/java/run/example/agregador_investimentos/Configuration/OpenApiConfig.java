package run.example.agregador_investimentos.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean // Spring IoC Container gerenciará esse objeto externo
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Serviço Backend de Agregador de Investimentos integrado à Bolsa de Valores")
                        .version("v1")
                        .description("Web API de mercado financeiro")
                        .termsOfService("https://github.com/Andre-Carvalho85/api-agregador-investimentos-springboot")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.linkedin.com/in/andr%C3%A9-felipe-carvalho-dos-santos-206589313/")));
    }
}
