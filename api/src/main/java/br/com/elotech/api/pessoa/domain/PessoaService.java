package br.com.elotech.api.pessoa.domain;

import br.com.elotech.api.pessoa.exception.PessoaNotFoundException;
import br.com.elotech.api.util.IdGenerator;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa findById(String id) {
        return repository.findById(id).orElseThrow(() -> new PessoaNotFoundException(id));
    }

    public Pessoa insert(Pessoa pessoa) {
        pessoa.setId(IdGenerator.generate());
        return repository.save(pessoa);
    }


    public Pessoa update(Pessoa pessoaAtualizada) {
        return repository.findById(pessoaAtualizada.getId())
                .map(pessoa -> {
                    pessoa.setNome(pessoaAtualizada.getNome());
                    pessoa.setCpf(pessoaAtualizada.getCpf());
                    pessoa.setDataNascimento(pessoaAtualizada.getDataNascimento());
                    return repository.save(pessoa);
                })
                .orElseThrow(() -> new PessoaNotFoundException(pessoaAtualizada.getId()));
    }

    public void delete(Pessoa pessoaDeletada) {
        repository.delete(pessoaDeletada);
    }

    public Page<Pessoa> findAll(String filter, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        if(Strings.isNullOrEmpty(filter)){
            return repository.findAll(pageable);
        }else{
            return repository.findAllByNome(pageable, filter);
        }
    }

}
