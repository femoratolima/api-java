package br.com.elotech.api.contato.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, String> {
    Page<Contato> findAllByNome(Pageable pageable, String filter);
}
