package com.altran.desafio.controller;

import com.altran.desafio.exceptions.DesafioException;
import com.altran.desafio.model.Carrinho;
import com.altran.desafio.model.Usuario;
import com.altran.desafio.repos.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
/*
Criar telas de CRUD
Cada usuário deve ser único
Cada usuário pode criar um carrinho com n itens
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioControllerTest {
    @Autowired
    UsuarioController controller;
    @Autowired
    UsuarioRepository repository;
    private Usuario usuario;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();
        usuario = new Usuario();
        usuario.setEmail("email@email.com");
        usuario.setNome("NOME USUARIO");
    }

    @Test
    public void saveUsuarioOk() {
        controller.save(usuario);
        assertNotNull(usuario.getId());
    }

    @Test
    public void saveUsuarioNulo() {
        try {
            controller.save(null);
            fail("Nao pode persistir objeto nulo");
        }catch (DesafioException e){
            assertEquals("O usuario nao e valido.", e.getMessage());
        }
    }

    @Test
    public void saveUsuarioNomeNulo() {
        usuario.setNome(null);
        try {
            controller.save(usuario);
            fail("Nao pode persistir objeto nulo");
        }catch (DesafioException e){
            assertEquals("O usuario nao e valido.", e.getMessage());
        }
    }

    @Test
    public void saveUsuarioNomeVazio() {
        usuario.setNome("");
        try {
            controller.save(usuario);
            fail("Nao pode persistir objeto nulo");
        }catch (DesafioException e){
            assertEquals("O usuario nao e valido.", e.getMessage());
        }
    }

    @Test
    public void saveUsuarioEmailNulo() {
        usuario.setEmail(null);
        try {
            controller.save(usuario);
            fail("Nao pode persistir objeto nulo");
        }catch (DesafioException e){
            assertEquals("O usuario nao e valido.", e.getMessage());
        }
    }

    @Test
    public void saveUsuarioEmailVazio() {
        usuario.setEmail("");
        try {
            controller.save(usuario);
            fail("Nao pode persistir objeto nulo");
        }catch (DesafioException e){
            assertEquals("O usuario nao e valido.", e.getMessage());
        }
    }

    @Test
    public void saveUsuarioEmailDuplicado() {
        controller.save(usuario);
        usuario.setId(null);
        try {
            controller.save(usuario);
            fail("Nao pode persistir objeto nulo");
        }catch (Exception e){
            assertEquals("Ja existe usuario com este e-mail", e.getMessage());
        }
    }



    @Test
    public void delete() {
        controller.save(usuario);
        assertNotNull(usuario.getId());
        controller.delete(usuario);
        assertEquals(0, controller.findAll().size());

    }

    @Test
    public void findAll() {
        controller.save(usuario);
        Usuario outro = new Usuario();
        outro.setNome("OUTRO NOME");
        outro.setEmail("outro@email.com");
        controller.save(outro);
        assertEquals(2, controller.findAll().size());
    }

    @Test
    public void findById() {
        controller.save(usuario);
        Usuario outro = controller.findById(usuario.getId());
        assertEquals(usuario, outro);
    }

}