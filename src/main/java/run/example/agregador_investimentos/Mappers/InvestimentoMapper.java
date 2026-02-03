package run.example.agregador_investimentos.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import run.example.agregador_investimentos.Domain.Investimento.DTOs.ResponseInvestimento;
import run.example.agregador_investimentos.Domain.Investimento.Investimento;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvestimentoMapper {
    @Mapping(target = "idAcaoInvestimento", source = "acaoInvestimento.acaoId")
    @Mapping(target = "quantidade", source = "quantidade")
    @Mapping(target = "total", constant = "0.0") // Valor fixo conforme seu código original
    ResponseInvestimento toResponse(Investimento investimento);

    List<ResponseInvestimento> toResponseList(List<Investimento> investimentos);
    }

