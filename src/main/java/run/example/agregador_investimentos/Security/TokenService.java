package run.example.agregador_investimentos.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.Value;
import org.springframework.stereotype.Service;
import run.example.agregador_investimentos.Domain.Usuario.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Definida em variáveis de ambiente dentro da ferramenta adotada
    @Value("${@api.security.token.secret}")
    private String secret;

    public String geraToken(Usuario usuario){
        try{
            // Uso de secret para tornar a aplicação única
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getEmailUsuario())
                    .withExpiresAt(geraDataExpiracao())
                    .sign(algoritmo);
            return token;
        } catch (JWTCreationException e){
            throw new RuntimeException("Erro na geração do token: " + e.getMessage());
        }
    }

    private Instant geraDataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("03:00"));
    }
}
