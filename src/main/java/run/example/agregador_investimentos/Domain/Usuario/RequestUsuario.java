package run.example.agregador_investimentos.Domain.Usuario;

public record RequestUsuario(
        String nomeUsuario,
        String emailUsuario,
        String senhaUsuario
) {
}
