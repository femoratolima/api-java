package br.com.elotech.api.pessoa.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, String>{
    Page<Pessoa> findAllByNome(Pageable pageable, String nome);
}
