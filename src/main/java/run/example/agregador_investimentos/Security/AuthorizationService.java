package run.example.agregador_investimentos.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import run.example.agregador_investimentos.Domain.Usuario.Usuario;
import run.example.agregador_investimentos.Repository.UsuarioRepository;


// Responsável por integrar o modelo de usuário da aplicação com o mecanismo de autenticação do Spring Security.
// É invocado pelo AuthenticationManager durante o processo de autenticação para carregar o usuário a partir do banco.
@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmailUsuario(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        // Converte entidade para UserDetails contendo identidade, senha criptografada e roles
        return usuario;
    }
}