package br.com.elotech.api.contato.api;

import br.com.elotech.api.contato.domain.Contato;
import br.com.elotech.api.contato.domain.ContatoService;
import br.com.elotech.api.pessoa.domain.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping(path = "/contatos")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Contato> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping()
    public ResponseEntity<Page<Contato>> findAll(String filter, int page, int pageSize) {
        return ResponseEntity.ok().body(service.findAll(filter, page, pageSize));
    }

    @PostMapping
    public ResponseEntity<Contato> insert(@Valid @RequestBody Contato contato) {
        Contato contatoCriado = service.insert(contato);
        URI createdResponse = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(contatoCriado.getId()).build().toUri();
        return ResponseEntity.created(createdResponse).body(contatoCriado);
    }

    @PutMapping
    public ResponseEntity<Contato> update(@Valid @RequestBody Contato contato) {
        Contato contatoAlterado = service.update(contato);
        URI createdResponse = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(contatoAlterado.getId()).build().toUri();
        return ResponseEntity.created(createdResponse).body(contatoAlterado);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Contato> deleteById(@PathVariable String id) {
        Contato contatoDeletado = service.findById(id);
        service.delete(contatoDeletado);
        return ResponseEntity.ok().body(contatoDeletado);
    }


}
