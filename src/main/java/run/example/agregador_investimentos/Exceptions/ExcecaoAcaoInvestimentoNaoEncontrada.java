package run.example.agregador_investimentos.Exceptions;

public class ExcecaoAcaoInvestimentoNaoEncontrada extends run.example.agregador_investimentos.exceptions.ExcecaoBase {
    public ExcecaoAcaoInvestimentoNaoEncontrada(String id){
        super("Ação não encontrada para a identificação fornecida");
    }
}
