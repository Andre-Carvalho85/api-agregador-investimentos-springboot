package run.example.agregador_investimentos.Domain.Conta.DTOs;

import run.example.agregador_investimentos.Domain.Conta.Conta;

import java.util.UUID;

public record ResponseConta(
        UUID idConta,
        String descricao
) {
    // Metodo para instanciar DTO de resposta ao instanciar a classe
    public static ResponseConta fromEntity(Conta conta){
        return new ResponseConta(
                conta.getIdConta(),
                conta.getDescricao()
        );
    }
}