package run.example.agregador_investimentos.Exceptions;

public class ExcecaoContaNaoEncontrada extends run.example.agregador_investimentos.exceptions.ExcecaoBase {
    public ExcecaoContaNaoEncontrada(String id){
        super("Conta não encontrada para o id: " + id);
    }
}
