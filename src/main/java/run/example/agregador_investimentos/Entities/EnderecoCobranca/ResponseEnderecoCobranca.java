package run.example.agregador_investimentos.Entities.EnderecoCobranca;

import java.util.UUID;

public record ResponseEnderecoCobranca(
        UUID id,
        String rua,
        Integer numeroCasa
) {
    public static ResponseEnderecoCobranca fromEntity(EnderecoCobranca enderecoCobranca) {
        return new ResponseEnderecoCobranca(
                enderecoCobranca.getId(),
                enderecoCobranca.getRua(),
                enderecoCobranca.getNumero_casa()
        );
    }
}
