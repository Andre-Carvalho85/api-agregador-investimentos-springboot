package run.example.agregador_investimentos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import run.example.agregador_investimentos.Domain.Conta.Conta;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {
    List<Conta> findAll();
}
