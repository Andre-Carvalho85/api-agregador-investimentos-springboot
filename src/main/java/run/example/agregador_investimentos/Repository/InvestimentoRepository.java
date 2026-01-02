package run.example.agregador_investimentos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import run.example.agregador_investimentos.Domain.Investimento.Investimento;
import run.example.agregador_investimentos.Domain.Investimento.InvestimentoId;

import java.util.List;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, InvestimentoId> {
    List<Investimento> findAll();
}
