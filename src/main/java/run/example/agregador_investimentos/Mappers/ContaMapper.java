package run.example.agregador_investimentos.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import run.example.agregador_investimentos.Domain.Conta.Conta;
import run.example.agregador_investimentos.Domain.Conta.DTOs.ResponseConta;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    @Mapping(target = "idConta", source = "idConta")
    @Mapping(target = "descricao", source = "descricao")
    ResponseConta toResponse(Conta conta);

    List<ResponseConta> toResponseList(List<Conta> contas);
}
