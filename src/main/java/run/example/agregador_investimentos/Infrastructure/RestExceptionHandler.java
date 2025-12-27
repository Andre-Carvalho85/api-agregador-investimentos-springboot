package run.example.agregador_investimentos.Infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


// Tratamento global de exceções da aplicação e gerais do Java
// (Service chama exceção de domínio e controller advice o converte em HTTP)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    // Para erros não mapeados
    @ExceptionHandler(Exception.class)
    public MensagemErroRest excecaoGenerica(Exception e){
        MensagemErroRest mensagemErroRest = new MensagemErroRest(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno no servidor.");
        return mensagemErroRest;
    }
}
