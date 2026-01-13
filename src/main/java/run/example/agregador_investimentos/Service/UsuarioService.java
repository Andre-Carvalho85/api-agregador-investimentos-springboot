package run.example.agregador_investimentos.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import run.example.agregador_investimentos.Domain.Usuario.DTOs.RequestUsuario;
import run.example.agregador_investimentos.Domain.Usuario.DTOs.ResponseUsuario;
import run.example.agregador_investimentos.Domain.Usuario.Usuario;
import run.example.agregador_investimentos.Exceptions.EmailJaCadastradoException;
import run.example.agregador_investimentos.Exceptions.ExcecaoUsuarioNaoEncontrado;
import run.example.agregador_investimentos.Mappers.UsuarioMapper;
import run.example.agregador_investimentos.Repository.ContaRepository;
import run.example.agregador_investimentos.Repository.EnderecoCobrancaRepository;
import run.example.agregador_investimentos.Repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {


    // Injeção de dependência com @Service
    // Quando a classe é instanciada, o contrutor chama a classe que a implementa a interface repositório
    private UsuarioRepository usuarioRepository;
    private EnderecoCobrancaRepository enderecoCobrancaRepository;
    private ContaRepository contaRepository;
    private PasswordEncoder encoder;
    private UsuarioMapper usuarioMapper;
    private static final Logger logger = LogManager.getLogger(UsuarioService.class);

    public UsuarioService(UsuarioRepository usuarioRepository,
                           EnderecoCobrancaRepository enderecoCobrancaRepository,
                           ContaRepository contaRepository,
                           PasswordEncoder encoder,
                           UsuarioMapper usuarioMapper) {

        this.usuarioRepository = usuarioRepository;
        this.enderecoCobrancaRepository = enderecoCobrancaRepository;
        this.contaRepository = contaRepository;
        this.encoder = encoder;
        this.usuarioMapper = usuarioMapper;
    }

    public List<ResponseUsuario> listarUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAllByActiveTrue();
        // Stream API para conversão da coleção e Mapper para conversão entre DTO e Entidade
        logger.debug("Listagem realizada");
        return usuarios.stream()
                .map(usuarioMapper::entidadeParaDto)
                .toList();
    }

    // Com tipo Optional<T>, retorna DTO de resposta se tiver e Optional.empty() caso não possua
    public Optional<ResponseUsuario> buscarUsuarioPeloId(String idUsuario){
        var usuario = usuarioRepository.findById(UUID.fromString(idUsuario));
        return usuario.map(usuarioMapper::entidadeParaDto);
    }

    // Retornar somente Id, em vez da DTO. Tampouco, a classe inteira
    public UUID registrarUsuario(RequestUsuario requestUsuario){
        if(this.usuarioRepository.findByEmailUsuario(requestUsuario.emailUsuario()) == null) {
            // DTO -> Entity e criptografia
            Usuario novoUsuario = usuarioMapper.dtoParaEntidade(requestUsuario);

            novoUsuario.setSenhaUsuario(encoder.encode(requestUsuario.senhaUsuario()));
            novoUsuario.setActive(true);
            novoUsuario.setRole(requestUsuario.roleUsuario());

            usuarioRepository.save(novoUsuario);
            return novoUsuario.getIdUsuario();
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
                // Mapper cuida da cópia e Service da regra
                usuarioMapper.atualizarEntidade(requestUsuario, usuario);
            }
            usuario.setSenhaUsuario(
                    encoder.encode(requestUsuario.senhaUsuario())
            );
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
