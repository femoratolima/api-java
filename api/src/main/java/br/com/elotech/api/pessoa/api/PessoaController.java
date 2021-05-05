package br.com.elotech.api.pessoa.api;

import br.com.elotech.api.pessoa.domain.Pessoa;
import br.com.elotech.api.pessoa.domain.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping(path = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping()
    public ResponseEntity<Page<Pessoa>> findAll(String filter, int page, int pageSize) {
        return ResponseEntity.ok().body(service.findAll(filter, page, pageSize));
    }

    @PostMapping
    public ResponseEntity<Pessoa> insert(@Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaCriada = service.insert(pessoa);
        URI createdResponse = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(pessoaCriada.getId()).build().toUri();
        return ResponseEntity.created(createdResponse).body(pessoaCriada);
    }

    @PutMapping
    public ResponseEntity<Pessoa> update(@Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaAlterada = service.update(pessoa);
        URI createdResponse = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(pessoaAlterada.getId()).build().toUri();
        return ResponseEntity.created(createdResponse).body(pessoaAlterada);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Pessoa> deleteById(@PathVariable String id) {
        Pessoa pessoaDeletada = service.findById(id);
        service.delete(pessoaDeletada);
        return ResponseEntity.ok().body(pessoaDeletada);
    }

}
