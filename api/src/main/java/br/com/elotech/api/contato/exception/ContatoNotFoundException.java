package br.com.elotech.api.contato.exception;

public class ContatoNotFoundException extends RuntimeException{

    public ContatoNotFoundException(String id) {
        super("Contato não encontrado: " + id);
    }

}
