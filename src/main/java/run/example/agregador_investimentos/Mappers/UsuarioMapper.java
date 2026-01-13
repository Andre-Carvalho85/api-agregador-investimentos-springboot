package run.example.agregador_investimentos.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import run.example.agregador_investimentos.Domain.Usuario.DTOs.RequestUsuario;
import run.example.agregador_investimentos.Domain.Usuario.DTOs.ResponseUsuario;
import run.example.agregador_investimentos.Domain.Usuario.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario dtoParaEntidade(RequestUsuario requestUsuario);
    ResponseUsuario entidadeParaDto(Usuario usuario);

    void atualizarEntidade(
            RequestUsuario dto,
            @MappingTarget Usuario usuario
    );
}
