package br.com.elotech.api.pessoa.domain;

import br.com.elotech.api.contato.domain.Contato;
import br.com.elotech.api.util.IdGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class PessoaServiceTest {

    @MockBean
    private PessoaRepository pessoaRepository;

    @SpyBean
    private PessoaService pessoaService;

    private String idGerado;
    private Contato contato;
    private Pessoa pessoa;
    private Page<Pessoa> pessoaPage;


    @Before
    public void inicializar(){
        List<Contato> listaContatos = new ArrayList<>();
        List<Pessoa> listaPessoas = new ArrayList<>();

        idGerado = IdGenerator.generate();

        contato = Contato.builder()
                .id(idGerado)
                .nome("Teste unitário")
                .email("testeunitario@teste.com")
                .telefone(1234567890L)
                .build();

        pessoa = Pessoa.builder()
                .id(idGerado)
                .nome("Teste unitário")
                .cpf("47905775895")
                .dataNascimento(new Date())
                .contatos(listaContatos)
                .build();

        listaPessoas.add(pessoa);
        listaContatos.add(contato);

        pessoaPage = new PageImpl<Pessoa>(listaPessoas);
    }

    @Test
    public void findById() {
        Mockito.when(pessoaRepository.findById(Mockito.any())).thenReturn(Optional.of(pessoa));
        Assert.assertEquals(pessoa,pessoaService.findById(pessoa.getId()));
    }

    @Test
    public void insert() {
        Mockito.when(pessoaRepository.save(Mockito.any())).thenReturn(pessoa);
        Assert.assertEquals(pessoa, pessoaService.insert(pessoa));
    }

    @Test
    public void update() {
        Mockito.when(pessoaRepository.findById(Mockito.any())).thenReturn(Optional.of(pessoa));
        Mockito.when(pessoaRepository.save(Mockito.any())).thenReturn(pessoa);

        Assert.assertEquals(pessoa, pessoaService.update(pessoa));
    }

    @Test
    public void delete() {
        pessoaService.delete(pessoa);
    }

    @Test
    public void findAll_FilterNull() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(pessoaRepository.findAll(pageable)).thenReturn(pessoaPage);

        Assert.assertEquals(pessoaPage, pessoaService.findAll(null,0,10));
    }

    @Test
    public void findAll_Filter() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(pessoaRepository.findAllByNome(pageable, "teste")).thenReturn(pessoaPage);

        Assert.assertEquals(pessoaPage, pessoaService.findAll("teste",0,10));
    }
}