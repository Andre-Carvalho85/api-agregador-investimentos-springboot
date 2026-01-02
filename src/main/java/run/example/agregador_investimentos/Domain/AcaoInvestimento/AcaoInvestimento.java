package run.example.agregador_investimentos.Domain.AcaoInvestimento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_acao_investimento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AcaoInvestimento {
    @Id
    @Column(name = "cd_ticker")
    private String acaoId; // Ticker da acao, como PETR4, MGLU4, etc.

    @Column(name = "ds_acao")
    public String descricao;
}
