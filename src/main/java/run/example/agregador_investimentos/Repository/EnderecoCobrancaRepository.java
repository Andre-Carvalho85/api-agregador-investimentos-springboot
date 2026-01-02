package run.example.agregador_investimentos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import run.example.agregador_investimentos.Domain.EnderecoCobranca.EnderecoCobranca;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnderecoCobrancaRepository extends JpaRepository<EnderecoCobranca, UUID> {
    List<EnderecoCobranca> findAll();
}
