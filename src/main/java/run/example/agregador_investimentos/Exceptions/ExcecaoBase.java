package run.example.agregador_investimentos.exceptions;

public abstract class ExcecaoBase extends RuntimeException {

    public ExcecaoBase(String mensagem) { // Para mensagens de erro personalizadas
        super(mensagem);
    }
}