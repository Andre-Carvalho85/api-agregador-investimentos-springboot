package run.example.agregador_investimentos.Domain.AcaoInvestimento.DTOs;

import run.example.agregador_investimentos.Domain.AcaoInvestimento.AcaoInvestimento;

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