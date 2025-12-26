package run.example.agregador_investimentos.Entities.Investimento;

import java.util.UUID;

public record ResponseInvestimento(
        String idAcaoInvestimento,
        Integer quantidade,
        double total
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