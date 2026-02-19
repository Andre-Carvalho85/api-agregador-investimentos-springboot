package run.example.agregador_investimentos.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import run.example.agregador_investimentos.Domain.Usuario.DTOs.RequestUsuario;
import run.example.agregador_investimentos.Domain.Usuario.DTOs.ResponseUsuario;
import run.example.agregador_investimentos.Domain.Usuario.Usuario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    ResponseUsuario entidadeParaDto(Usuario usuario);

    Usuario dtoParaEntidade(RequestUsuario requestUsuario);

    List<ResponseUsuario> entidadeParaDto(List<Usuario> usuarios);

    void atualizarEntidade(RequestUsuario dto, @MappingTarget Usuario usuario);
}
