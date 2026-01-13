package run.example.agregador_investimentos.Domain.Conta.DTOs;

// DTO para conta e endereco de pagamento
public record RequestConta(
    String descricao,
    String rua,
    Integer numero
) {
}
