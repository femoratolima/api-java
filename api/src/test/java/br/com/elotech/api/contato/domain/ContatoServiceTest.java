package br.com.elotech.api.contato.domain;

import br.com.elotech.api.contato.domain.Contato;
import br.com.elotech.api.contato.domain.ContatoRepository;
import br.com.elotech.api.contato.domain.ContatoService;
import br.com.elotech.api.pessoa.domain.Pessoa;
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
import br.com.elotech.api.util.IdGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class ContatoServiceTest {

    @MockBean
    private ContatoRepository contatoRepository;

    @SpyBean
    private ContatoService contatoService;

    private String idGerado;
    private Contato contato;
    private Pessoa pessoa;
    private Page<Contato> contatoPage;


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

        contatoPage = new PageImpl<Contato>(listaContatos);
    }

    @Test
    public void findById() {
        Mockito.when(contatoRepository.findById(Mockito.any())).thenReturn(Optional.of(contato));
        Assert.assertEquals(contato,contatoService.findById(contato.getId()));
    }

    @Test
    public void insert() {
        Mockito.when(contatoRepository.save(Mockito.any())).thenReturn(contato);
        Assert.assertEquals(contato, contatoService.insert(contato));
    }

    @Test
    public void update() {
        Mockito.when(contatoRepository.findById(Mockito.any())).thenReturn(Optional.of(contato));
        Mockito.when(contatoRepository.save(Mockito.any())).thenReturn(contato);

        Assert.assertEquals(contato, contatoService.update(contato));
    }

    @Test
    public void delete() {
        contatoService.delete(contato);
    }

    @Test
    public void findAll_FilterNull() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(contatoRepository.findAll(pageable)).thenReturn(contatoPage);

        Assert.assertEquals(contatoPage, contatoService.findAll(null,0,10));
    }

    @Test
    public void findAll_Filter() {
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(contatoRepository.findAllByNome(pageable, "teste")).thenReturn(contatoPage);

        Assert.assertEquals(contatoPage, contatoService.findAll("teste",0,10));
    }
}