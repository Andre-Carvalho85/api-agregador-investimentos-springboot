package run.example.agregador_investimentos.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import run.example.agregador_investimentos.Domain.Usuario.RequestUsuario;
import run.example.agregador_investimentos.Domain.Usuario.ResponseUsuario;
import run.example.agregador_investimentos.Domain.Usuario.Usuario;
import run.example.agregador_investimentos.Exceptions.EmailJaCadastradoException;
import run.example.agregador_investimentos.Exceptions.ExcecaoUsuarioNaoEncontrado;
import run.example.agregador_investimentos.Repository.ContaRepository;
import run.example.agregador_investimentos.Repository.EnderecoCobrancaRepository;
import run.example.agregador_investimentos.Repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    // Injeção de dependência com @Service
    // Quando a classe é instanciada, o contrutor chama a classe que a implementa a interface repositório
    private UsuarioRepository usuarioRepository;
    private EnderecoCobrancaRepository enderecoCobrancaRepository;
    private ContaRepository contaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                           EnderecoCobrancaRepository enderecoCobrancaRepository,
                           ContaRepository contaRepository){

        this.usuarioRepository = usuarioRepository;
        this.enderecoCobrancaRepository = enderecoCobrancaRepository;
        this.contaRepository = contaRepository;

    }

    public List<ResponseUsuario> listarUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAllByActiveTrue();
        // Entity -> DTO usando Stream API, que pega a lista de entidades e as mapeia para a lista de DTOs de resposta
        return usuarios.stream()
                .map(ResponseUsuario::fromEntity)
                .collect(Collectors.toList());
    }

    // Com tipo Optional<T>, retorna DTO de resposta se tiver e Optional.empty() caso não possua
    public Optional<ResponseUsuario> buscarUsuarioPeloId(String idUsuario){
        var usuario = usuarioRepository.findById(UUID.fromString(idUsuario));
        return usuario.map(ResponseUsuario::fromEntity);
    }

    // Retornar somente Id, em vez da DTO. Tampouco, a classe inteira
    public UUID registrarUsuario(RequestUsuario requestUsuario){
        if(this.usuarioRepository.findByEmailUsuario(requestUsuario.emailUsuario()) == null) {
            // DTO -> Entity e criptografia
            String senhaCriptografada = new BCryptPasswordEncoder().encode(requestUsuario.senhaUsuario());

            Usuario novoUsuario = new Usuario(requestUsuario);
            novoUsuario.setSenhaUsuario(senhaCriptografada);

            Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
            return usuarioSalvo.getIdUsuario();
        } else {
            throw new EmailJaCadastradoException("E-mail já está em uso.");
        }
    }

    public void atualizarUsuario(RequestUsuario requestUsuario, String idUsuario){
        Optional<Usuario> optionalUsuario= usuarioRepository.findById(UUID.fromString(idUsuario));
        if (optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            if (usuario.getNomeUsuario() != null &&
                usuario.getEmailUsuario() != null &&
                usuario.getSenhaUsuario() != null){
                usuario.setNomeUsuario(requestUsuario.nomeUsuario());
                usuario.setEmailUsuario(requestUsuario.emailUsuario());
                usuario.setSenhaUsuario(requestUsuario.senhaUsuario());
            }
            usuarioRepository.save(usuario);
        } else {
            throw new ExcecaoUsuarioNaoEncontrado(idUsuario);
        }
    }

    public void deletarUsuario(String idUsuario){
        Optional<Usuario> optionalUsuario= usuarioRepository.findById(UUID.fromString(idUsuario));
        if (optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            usuario.setActive(false);
        } else {
            throw new ExcecaoUsuarioNaoEncontrado(idUsuario);
        }
    }
}
