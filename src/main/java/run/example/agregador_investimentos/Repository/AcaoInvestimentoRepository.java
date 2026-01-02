package run.example.agregador_investimentos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import run.example.agregador_investimentos.Domain.AcaoInvestimento.AcaoInvestimento;

import java.util.List;

@Repository
public interface AcaoInvestimentoRepository extends JpaRepository<AcaoInvestimento, String> {
    List<AcaoInvestimento> findAll();
}
