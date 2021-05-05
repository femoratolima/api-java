package br.com.elotech.api.contato.domain;

import br.com.elotech.api.pessoa.domain.Pessoa;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contatos")
public class Contato {

    @Id
    @Column(name = "id_contato")
    private String id;

    @NotEmpty(message = "Nome não pode ser vazio.")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone", nullable = false)
    private Long telefone;

    @Email
    @NotEmpty(message = "Email não pode ser vazio.")
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @NotNull
    @JoinColumn(name="id_pessoa")
    private Pessoa pessoa;

}
