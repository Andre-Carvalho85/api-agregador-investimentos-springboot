package run.example.agregador_investimentos.Service;

import org.springframework.stereotype.Service;
import run.example.agregador_investimentos.Domain.AcaoInvestimento.AcaoInvestimento;
import run.example.agregador_investimentos.Domain.AcaoInvestimento.DTOs.RequestAcaoInvestimento;
import run.example.agregador_investimentos.Repository.AcaoInvestimentoRepository;

@Service
public class AcaoInvestimentoService {
    private AcaoInvestimentoRepository acaoInvestimentoRepository;

    public AcaoInvestimentoService(AcaoInvestimentoRepository acaoInvestimentoRepository){

        this.acaoInvestimentoRepository = acaoInvestimentoRepository;
    }

    public void registrarAcao(RequestAcaoInvestimento requestAcaoInvestimento) {
        var acao = new AcaoInvestimento(
                requestAcaoInvestimento.acaoId(),
                requestAcaoInvestimento.descricao()
        );
        acaoInvestimentoRepository.save(acao);
    }
}
