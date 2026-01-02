package run.example.agregador_investimentos.Domain.Investimento;

public record RequestInvestimento(
        String idAcaoInvestimento,
        Integer quantidade
) {
}