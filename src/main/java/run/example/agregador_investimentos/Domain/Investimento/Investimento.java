package run.example.agregador_investimentos.Domain.Investimento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import run.example.agregador_investimentos.Domain.AcaoInvestimento.AcaoInvestimento;
import run.example.agregador_investimentos.Domain.Conta.Conta;

import java.math.BigDecimal;

// Entidade associativa para Conta e AcaoInvestimento: acoes de determinada conta
@Entity
@Table(name = "tb_conta_acao_investimento")
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

    @Column(name = "qtde_total", columnDefinition = "DECIMAL(15,2)", precision = 15, scale = 2)
    private BigDecimal total; // Por ser classe, aceita valor nulo
}
