package run.example.agregador_investimentos.Domain.AcaoInvestimento.DTOs;

public record RequestAcaoInvestimento(
        String acaoId,
        String descricao
) {
}