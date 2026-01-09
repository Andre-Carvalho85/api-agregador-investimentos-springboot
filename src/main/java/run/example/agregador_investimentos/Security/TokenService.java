package run.example.agregador_investimentos.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;
import run.example.agregador_investimentos.Domain.Usuario.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    // Definida em variáveis de ambiente dentro da ferramenta adotada
    @Value("${api.security.token.secret}")
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

    public String validarToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }

    private Instant geraDataExpiracao(){
        // Usa o relógio universal (UTC) e adiciona 2 horas
        return Instant.now().plus(2, ChronoUnit.HOURS);
    }
}
