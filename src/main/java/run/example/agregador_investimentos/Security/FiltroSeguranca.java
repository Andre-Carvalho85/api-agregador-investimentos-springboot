package run.example.agregador_investimentos.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import run.example.agregador_investimentos.Repository.UsuarioRepository;

import java.io.IOException;

// Token é armazenado no cliente e o servidor apenas valida a cada request
@Component
public class FiltroSeguranca extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    private String buscarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        // Retira Bearer para deixar somente o token
        return authHeader.replace("Bearer", "");
    }

    // Metodo chamado apos autorização na SecurityFilterChain
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.buscarToken(request);
        if(token != null){
            var login = tokenService.validarToken(token);
            UserDetails usuario = usuarioRepository.findByEmailUsuario(login);

            // Gera o token e traz autenticacao e autorizacao
            var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }
        filterChain.doFilter(request, response);
    }
}
