package run.example.agregador_investimentos.Domain.Usuario.DTOs;

import run.example.agregador_investimentos.Security.Enum.RolesUsuario;

public record RequestUsuario(
        String nomeUsuario,
        String emailUsuario,
        String senhaUsuario,
        RolesUsuario roleUsuario
) {
}
