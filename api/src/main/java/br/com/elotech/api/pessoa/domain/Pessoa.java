package br.com.elotech.api.pessoa.domain;

import br.com.elotech.api.contato.domain.Contato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @Column(name = "id_pessoa")
    private String id;

    @NotEmpty(message = "Nome não pode ser vazio.")
    @NotNull(message = "Nome não pode ser nulo.")
    private String nome;

    @NotEmpty(message = "CPF não pode ser vazio.")
    @CPF(message="CPF inválido")
    private String cpf;

    @Past(message = "Data inválida")
    @Column(name = "data_nasc", nullable = false)
    private Date dataNascimento;

    @JsonIgnore
    @OneToMany(mappedBy ="pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contatos = new ArrayList<>();

}
