package run.example.agregador_investimentos.Domain.Investimento;

import java.math.BigDecimal;

public record ResponseInvestimento(
        String idAcaoInvestimento,
        Integer quantidade,
        BigDecimal total
) {
    public static ResponseInvestimento fromEntity(Investimento investimento) {
        return new ResponseInvestimento(
        // Extrai os valores individuais da PK Composta
                investimento.getId().getIdAcaoInvestimento(),
                investimento.getQuantidade(),
                investimento.getTotal()
        );
    }
}