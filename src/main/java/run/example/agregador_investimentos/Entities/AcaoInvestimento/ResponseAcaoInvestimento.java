package run.example.agregador_investimentos.Entities.AcaoInvestimento;

public record ResponseAcaoInvestimento(
        String acaoId,
        String descricao
) {
    public static ResponseAcaoInvestimento fromEntity(AcaoInvestimento acao) {
        return new ResponseAcaoInvestimento(
                acao.getAcaoId(),
                acao.getDescricao()
        );
    }
}