package run.example.agregador_investimentos.Entities.Investimento;

import java.util.UUID;

public record RequestInvestimento(
        UUID idConta,
        String idAcaoInvestimento,
        Integer quantidade
) {
}