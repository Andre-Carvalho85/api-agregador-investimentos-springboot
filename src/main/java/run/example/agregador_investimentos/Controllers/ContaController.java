package run.example.agregador_investimentos.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import run.example.agregador_investimentos.Domain.Conta.DTOs.RequestConta;
import run.example.agregador_investimentos.Domain.Conta.DTOs.ResponseConta;
import run.example.agregador_investimentos.Domain.Investimento.DTOs.RequestInvestimento;
import run.example.agregador_investimentos.Domain.Investimento.DTOs.ResponseInvestimento;
import run.example.agregador_investimentos.Service.ContaService;
import run.example.agregador_investimentos.Service.UsuarioService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/contas")
public class ContaController {
    private ContaService contaService;
    private UsuarioService usuarioService;

    public ContaController(ContaService contaService,
                           UsuarioService usuarioService){
        this.contaService = contaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{idUsuario}/contas")
    public ResponseEntity<List<ResponseConta>> listarContasPorUsuario(@PathVariable("idUsuario") String idUsuario){
        var contas = contaService.listarContasPorUsuario(idUsuario);
        return ResponseEntity.ok(contas);
    }

    // Criação de conta e endereco de pagamento para determinado usuario
    @PostMapping("/{idUsuario}/contas")
    public ResponseEntity<ResponseConta> registrarConta(@PathVariable("idUsuario") String idUsuario,
                                                        @RequestBody RequestConta requestConta){
        contaService.criarConta(idUsuario, requestConta);
        return ResponseEntity.created(URI.create("/v1/users/" + idUsuario.toString())).build();
    }

    // Associação de ações a uma conta
    @PostMapping("/{idConta}/acoes")
    public ResponseEntity<Void> associarAcoesPraConta(@PathVariable("idConta") String idConta,
                                                                      @RequestBody RequestInvestimento requestInvestimento){
        contaService.associarAcoesPraConta(idConta, requestInvestimento);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idConta}/acoes")
    public ResponseEntity<List<ResponseInvestimento>> listarAcoesConta(@PathVariable("idConta") String idConta){
        var acoes = contaService.listarAcoesConta(idConta);

        return ResponseEntity.ok(acoes);
    }
}
