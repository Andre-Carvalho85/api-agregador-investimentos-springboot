package run.example.agregador_investimentos.Security.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.example.agregador_investimentos.Security.Dtos.AuthenticationDTO;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    // Utilização de BCrypt encoder
    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO dto){
        var usuarioSenha = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth = this.authenticationManager.authenticate(usuarioSenha);

        return ResponseEntity.ok().build();
    }
}
