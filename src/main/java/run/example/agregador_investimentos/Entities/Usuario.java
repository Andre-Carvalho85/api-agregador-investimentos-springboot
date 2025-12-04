package run.example.agregador_investimentos.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = " tb_usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Metodos para comparacao comparando sem ser por referência (pelos dados em si) e fazendo uso de hash (mesmo valor, mesmo resultado)
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    @Column(name = "cd_usuario")
    private UUID idUsuario;

    @Column(name = "nm_usuario")
    private String nomeUsuario;

    @Column(name = "ds_email_usuario")
    private String emailUsuario;

    // Registros automáticos de criação e atualização
    @CreationTimestamp
    @Column(name = "dt_criacao_usuario")
    private Instant criacao_entidade;

    @UpdateTimestamp
    @Column(name = "dt_atualizacao_usuario")
    private Instant atualizacao_entidade;

}
