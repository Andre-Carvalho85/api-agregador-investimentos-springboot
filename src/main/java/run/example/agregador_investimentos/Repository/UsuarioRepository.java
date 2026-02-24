package run.example.agregador_investimentos.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import run.example.agregador_investimentos.Domain.Usuario.Usuario;

import org.springframework.data.domain.Pageable;
import run.example.agregador_investimentos.Security.Enum.RolesUsuario;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    // Padrão JPA que abstrai as queries (select pelos ativos e autorização)
    Page<Usuario> findAllByActiveTrue(Pageable pageable);
    UserDetails findByEmailUsuario(String email);

    // Novo metodo para filtro
    Page<Usuario> findByRole(RolesUsuario rolesUsuario, Pageable pageable);
}
