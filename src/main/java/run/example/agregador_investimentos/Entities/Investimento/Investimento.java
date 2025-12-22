package run.example.agregador_investimentos.Entities.Investimento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import run.example.agregador_investimentos.Entities.AcaoInvestimento.AcaoInvestimento;
import run.example.agregador_investimentos.Entities.Conta.Conta;

// Entidade associativa para Conta e AcaoInvestimento: acoes de determinada conta
@Entity
@Table(name = "tb_conta_acaoInvestimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investimento {
    @EmbeddedId
    private InvestimentoId id;

    @ManyToOne
    @MapsId("idConta")
    @JoinColumn(name = "cd_conta")
    private Conta conta;

    @ManyToOne
    @MapsId("idAcaoInvestimento")
    @JoinColumn(name = "cd_acao_investimento")
    private AcaoInvestimento acaoInvestimento;

    @Column(name = "qtd_acoes")
    private Integer quantidade;
}
