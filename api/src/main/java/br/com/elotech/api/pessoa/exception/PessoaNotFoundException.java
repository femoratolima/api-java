package br.com.elotech.api.pessoa.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PessoaNotFoundException extends RuntimeException{

    public PessoaNotFoundException(String id) {
        super("Pessoa não encontrada: " + id);
    }


}
