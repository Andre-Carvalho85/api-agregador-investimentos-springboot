package run.example.agregador_investimentos.Domain.Investimento.DTOs;

public record RequestInvestimento(
        String idAcaoInvestimento,
        Integer quantidade
) {
}