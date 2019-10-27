package com.altran.desafio.controller;

import com.altran.desafio.exceptions.DesafioException;
import com.altran.desafio.exceptions.ModeloInvalidoException;
import com.altran.desafio.model.Item;
import com.altran.desafio.repos.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemControllerTest {

    @Autowired
    ItemController controller;

    @Autowired
    ItemRepository repository;

    private Item item;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();
        item = new Item();
        item.setNome("NOME ITEM");
        item.setValor(100d);
    }


    @Test
    public void saveItemOk() {
        controller.save(item);
        assertNotNull(item.getId());
    }
    @Test
    public void saveItemNulo() {
        try {
            controller.save(null);
            fail("Nao pode persistir objeto nulo");
        }catch (DesafioException exception){
            assertEquals("O item nao e valido.", exception.getMessage());
        }
    }

    @Test
    public void saveItemNomeNulo() {
        item.setNome(null);
        try {
            controller.save(item);
            fail("Nao pode persistir este objeto");
        }catch (DesafioException exception){
            assertEquals("O item nao e valido.", exception.getMessage());
        }
    }

    @Test
    public void saveItemNomeVazio() {
        item.setNome("");
        try {
            controller.save(item);
            fail("Nao pode persistir este objeto");
        }catch (DesafioException exception){
            assertEquals("O item nao e valido.", exception.getMessage());
        }
    }

    @Test
    public void saveItemValorNulo() {
        item.setValor(null);
        try {
            controller.save(item);
            fail("Nao pode persistir este objeto");
        }catch (DesafioException exception){
            assertEquals("O item nao e valido.", exception.getMessage());
        }
    }

    @Test
    public void saveItemValorNegativo() {
        item.setValor(-1d);
        try {
            controller.save(item);
            fail("Nao pode persistir este objeto");
        }catch (DesafioException exception){
            assertEquals("O item nao e valido.", exception.getMessage());
        }
    }

    @Test
    public void delete() {
        controller.save(item);
        assertEquals(1,controller.findAll().size());
        controller.delete(item);
        assertEquals(0,controller.findAll().size());
    }

    @Test
    public void findAll() {
        controller.save(item);
        controller.save(new Item("OUTRO NOME", 111d));
        List<Item> items = controller.findAll();
        assertEquals(2, items.size());

    }

    @Test
    public void findById() {
        controller.save(item);
        Item outroItem = controller.findById(item.getId());
        assertEquals(item, outroItem);
    }
}