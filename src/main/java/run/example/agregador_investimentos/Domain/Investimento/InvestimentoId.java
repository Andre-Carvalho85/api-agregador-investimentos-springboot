package run.example.agregador_investimentos.Domain.Investimento;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.UUID;

// Identificador formado pelas FK de Conta e AcaoInvestimento
@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class InvestimentoId {
    @Column(name = "cd_conta")
    private UUID idConta;

    @Column(name = "cd_ticker")
    private String idAcaoInvestimento;
}
