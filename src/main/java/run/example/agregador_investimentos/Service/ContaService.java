package run.example.agregador_investimentos.Service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import run.example.agregador_investimentos.Domain.Conta.Conta;
import run.example.agregador_investimentos.Domain.Conta.RequestConta;
import run.example.agregador_investimentos.Domain.Conta.ResponseConta;
import run.example.agregador_investimentos.Domain.EnderecoCobranca.EnderecoCobranca;
import run.example.agregador_investimentos.Domain.Investimento.Investimento;
import run.example.agregador_investimentos.Domain.Investimento.InvestimentoId;
import run.example.agregador_investimentos.Domain.Investimento.RequestInvestimento;
import run.example.agregador_investimentos.Domain.Investimento.ResponseInvestimento;
import run.example.agregador_investimentos.Exceptions.ExcecaoAcaoInvestimentoNaoEncontrada;
import run.example.agregador_investimentos.Exceptions.ExcecaoContaNaoEncontrada;
import run.example.agregador_investimentos.Exceptions.ExcecaoUsuarioNaoEncontrado;
import run.example.agregador_investimentos.Repository.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ContaService {
    private final UsuarioRepository usuarioRepository;
    private final EnderecoCobrancaRepository enderecoCobrancaRepository;
    private final ContaRepository contaRepository;
    private final AcaoInvestimentoRepository acaoInvestimentoRepository;
    private final InvestimentoRepository investimentoRepository;

    public ContaService(UsuarioRepository usuarioRepository,
                        EnderecoCobrancaRepository enderecoCobrancaRepository,
                        ContaRepository contaRepository,
                        AcaoInvestimentoRepository acaoInvestimentoRepository,
                        InvestimentoRepository investimentoRepository){

        this.usuarioRepository = usuarioRepository;
        this.enderecoCobrancaRepository = enderecoCobrancaRepository;
        this.contaRepository = contaRepository;
        this.acaoInvestimentoRepository = acaoInvestimentoRepository;
        this.investimentoRepository = investimentoRepository;
    }

    public List<ResponseConta> listarContasPorUsuario(String idUsuario){
        var usuario = usuarioRepository.findById(UUID.fromString(idUsuario))
                .orElseThrow(() -> new ExcecaoUsuarioNaoEncontrado(idUsuario));

        //Retorna uma lista de DTOs
        return usuario.getContas()
                .stream()
                .map(ac ->
                        new ResponseConta(ac.getIdConta(), ac.getDescricao()))
                .toList();
    }

    @Transactional
    public void criarConta(String idUsuario, RequestConta requestConta){
        var usuario = usuarioRepository.findById(UUID.fromString((idUsuario)))
                .orElseThrow(() -> new ExcecaoUsuarioNaoEncontrado(idUsuario));
        var conta = new Conta();
        conta.setUsuario(usuario);
        conta.setDescricao(requestConta.descricao());

        var enderecoCobranca = new EnderecoCobranca();
        enderecoCobranca.setRua(requestConta.rua());
        enderecoCobranca.setNumero_casa(requestConta.numero());

        enderecoCobranca.setConta(conta);
        conta.setEnderecoCobranca(enderecoCobranca);

        contaRepository.save(conta);

        enderecoCobrancaRepository.save(enderecoCobranca);
    }

    public void associarAcoesPraConta(String idConta, RequestInvestimento requestInvestimento){
        // Investimento é a entidade associativa das ações e suas contas, então tem de verificar a existência de ambas antes

        var conta = contaRepository.findById(UUID.fromString(idConta))
                .orElseThrow(() -> new ExcecaoContaNaoEncontrada(idConta));

        var acao = acaoInvestimentoRepository.findById(requestInvestimento.idAcaoInvestimento())
                .orElseThrow(() -> new ExcecaoAcaoInvestimentoNaoEncontrada(idConta));

         // DTO -> Entity
        var id = new InvestimentoId(conta.getIdConta(), acao.getAcaoId());
        var entidade = new Investimento(id, conta, acao, requestInvestimento.quantidade(), BigDecimal.valueOf(0.0));

        investimentoRepository.save(entidade);
    }

    public List<ResponseInvestimento> listarAcoesConta(String idConta) {
        var conta = contaRepository.findById(UUID.fromString(idConta))
                .orElseThrow(() -> new ExcecaoContaNaoEncontrada(idConta));
        return conta.getInvestimentosConta()
                .stream()
                .map(as -> new ResponseInvestimento(
                        as.getAcaoInvestimento().getAcaoId(),
                        as.getQuantidade(),
                        BigDecimal.valueOf(0.0)
                ))
                .toList();
    }
}
