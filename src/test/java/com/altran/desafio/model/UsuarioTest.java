package com.altran.desafio.model;

import com.altran.desafio.exceptions.ModeloInvalidoException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsuarioTest {

    private Usuario usuario;

    @Before
    public void setUp() throws Exception {
        usuario = new Usuario();
        usuario.setNome("NOME USUARIO");
        usuario.setEmail("email@email.com");
    }

    @Test
    public void validaUsuarioOK() {
        try {
            usuario.valida();
        } catch (ModeloInvalidoException e) {
            e.printStackTrace();
            fail("Nao deveria lancar excecao");
        }
    }

    @Test
    public void validaUsuarioNomeNulo() {
        usuario.setNome(null);
        try {
            usuario.valida();
            fail("Deveria lancar excecao");
        } catch (ModeloInvalidoException e) {
            assertEquals("Nome deve ser informado.", e.getMessage());
        }
    }
    @Test
    public void validaUsuarioNomeVazio() {
        usuario.setNome("");
        try {
            usuario.valida();
            fail("Deveria lancar excecao");
        } catch (ModeloInvalidoException e) {
            assertEquals("Nome deve ser informado.", e.getMessage());
        }
    }

    @Test
    public void validaUsuarioEmailNulo() {
        usuario.setEmail(null);
        try {
            usuario.valida();
            fail("Deveria lancar excecao");
        } catch (ModeloInvalidoException e) {
            assertEquals("Email deve ser informado.", e.getMessage());
        }
    }
    @Test
    public void validaUsuarioEmailVazio() {
        usuario.setEmail("");
        try {
            usuario.valida();
            fail("Deveria lancar excecao");
        } catch (ModeloInvalidoException e) {
            assertEquals("Email deve ser informado.", e.getMessage());
        }
    }

    @Test
    public void abreCarrinho() {
        assertNull(usuario.getCarrinho());
        usuario.abreCarrinho();
        assertNotNull(usuario.getCarrinho());
        assertSame(usuario, usuario.getCarrinho().getUsuario());
    }

    @Test
    public void fechaCarrinho() {
        usuario.abreCarrinho();
        usuario.fechaCarrinho();
        assertNull(usuario.getCarrinho());
    }
}