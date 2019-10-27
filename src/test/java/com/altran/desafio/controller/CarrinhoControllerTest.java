package com.altran.desafio.controller;

import com.altran.desafio.exceptions.ModeloInvalidoException;
import com.altran.desafio.model.Carrinho;
import com.altran.desafio.model.Item;
import com.altran.desafio.model.Usuario;
import com.altran.desafio.repos.CarrinhoRepository;
import com.altran.desafio.repos.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CarrinhoControllerTest {
    @Autowired
    private CarrinhoController controller;

    @Autowired
    private CarrinhoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    private Carrinho carrinho;
    @Before
    public void setUp() throws Exception {
        repository.deleteAll();
        usuarioRepository.deleteAll();
        criaCarrinho("NOME USUARIO", "email@email.com");
    }

    @Test
    public void save() {
        controller.save(carrinho);
        assertEquals(1, controller.findAll().size());
        assertNotNull(carrinho.getId());
    }

    @Test
    public void delete() {
        controller.save(carrinho);
        assertEquals(1, controller.findAll().size());
        controller.delete(carrinho);
        assertEquals(0, controller.findAll().size());
    }

    @Test
    public void findAll() throws ModeloInvalidoException {

        carrinho.adicionarItem( new Item("ITEM A", 100d));
        controller.save(carrinho);

        criaCarrinho("NOME USUARIO 2", "outro@email.com");
        carrinho.adicionarItem(new Item("ITEM B", 10d));
        controller.save(carrinho);

        List<Carrinho> carrinhos = controller.findAll();
        assertEquals(2, carrinhos.size());
        Iterator<Carrinho> carrinhoIterator = carrinhos.iterator();
        assertEquals(new Double(10), carrinhoIterator.next().getTotal());
        assertEquals(new Double(100), carrinhoIterator.next().getTotal());
    }

    private void criaCarrinho(String s, String s2) {
        Usuario usuario = new Usuario();
        usuario.setNome(s);
        usuario.setEmail(s2);
        usuarioRepository.save(usuario);
        carrinho = usuario.abreCarrinho();
    }

    @Test
    public void findByUsuario() {
    }
}