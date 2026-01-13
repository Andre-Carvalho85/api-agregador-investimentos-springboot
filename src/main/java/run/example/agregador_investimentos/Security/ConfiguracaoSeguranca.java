package run.example.agregador_investimentos.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Todas as requisições passam por aqui antes de ir para qualquer controller.
// Para permitir filtros de segurança personalizados (SecurityFilterChain) e liberação de rotas

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca {

    @Autowired
    FiltroSeguranca filtroSeguranca;

    // Cadeia de segurança com autenticação e autorização
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // REST
                .authorizeHttpRequests(authorize -> authorize
                        // Filtros antes do token
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        // .requestMatchers(HttpMethod.POST, "/v1/usuarios").permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/acoes").hasRole("ADMIN")
                        .anyRequest().authenticated())
                // Filtros depois do token
                .addFilterBefore(filtroSeguranca, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Bean usado no AuthorizationController
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Hash para senhas
    @Bean
    public PasswordEncoder criptografarSenha(){
        return new BCryptPasswordEncoder();
    }

}
