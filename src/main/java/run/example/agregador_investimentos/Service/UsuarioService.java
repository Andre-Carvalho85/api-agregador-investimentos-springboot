package run.example.agregador_investimentos.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import run.example.agregador_investimentos.Entities.RequestUsuario;
import run.example.agregador_investimentos.Entities.Usuario;
import run.example.agregador_investimentos.Repository.UsuarioRepository;

import java.util.UUID;

@Service
public class UsuarioService {
    // Injeção de dependência com @Service
    // Quando a classe é instanciada, o contrutor chama a classe que a implementa a interface repositório
    private UsuarioRepository usuarioRepository;
    private UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    // Retornar somente Id, em vez da DTO. Tampouco, a classe inteira
    public UUID registrarUsuario(RequestUsuario requestUsuario){
        // DTO -> Entity
        Usuario novoUsuario = new Usuario(requestUsuario);
            usuarioRepository.save(novoUsuario);
            return novoUsuario.getIdUsuario();
    }

}
