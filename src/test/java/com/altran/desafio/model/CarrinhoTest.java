package com.altran.desafio.model;

import com.altran.desafio.exceptions.ModeloInvalidoException;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CarrinhoTest {
    private Carrinho carrinho;

    @Before
    public void setUp(){
        carrinho = new Usuario().abreCarrinho();
    }


    @Test
    public void adicionarItem() throws ModeloInvalidoException {
        Item item = new Item("ITEM TESTE", 100d);
        assertEquals(0, carrinho.verificaQtdeItens(item));
        carrinho.adicionarItem(item);
        assertEquals(1, carrinho.verificaQtdeItens(item));
    }

    @Test
    public void adicionar2ItensIguais() throws ModeloInvalidoException {
        Item item = new Item("1", "ITEM TESTE", 100d);
        assertEquals(0, carrinho.verificaQtdeItens(item));
        carrinho.adicionarItem(item);
        assertEquals(1, carrinho.verificaQtdeItens(item));
        carrinho.adicionarItem(item);
        assertEquals(2, carrinho.verificaQtdeItens(item));
    }

    @Test
    public void adicionar2ItensDiferentes() throws ModeloInvalidoException {
        Item item = new Item("1", "ITEM TESTE", 100d);
        assertEquals(0, carrinho.verificaQtdeItens(item));
        carrinho.adicionarItem(item);
        assertEquals(1, carrinho.verificaQtdeItens(item));
        Item outroItem = new Item("2", "OUTRO ITEM TESTE", 222d);
        carrinho.adicionarItem(outroItem);
        assertEquals(1, carrinho.verificaQtdeItens(item));
        assertEquals(1, carrinho.verificaQtdeItens(outroItem));
    }

    @Test
    public void removerItem() throws ModeloInvalidoException {
        Item item = new Item("1", "ITEM TESTE", 100d);
        assertEquals(0, carrinho.verificaQtdeItens(item));
        carrinho.adicionarItem(item);
        assertEquals(1, carrinho.verificaQtdeItens(item));
        carrinho.removerItem(item);
        assertEquals(0, carrinho.verificaQtdeItens(item));
        carrinho.removerItem(item);
        assertEquals(0, carrinho.verificaQtdeItens(item));
    }

    @Test
    public void removerItemInexistente() throws ModeloInvalidoException {
        Item item = new Item("1", "ITEM TESTE", 100d);
        assertEquals(0, carrinho.verificaQtdeItens(item));
        carrinho.removerItem(item);
        assertEquals(0, carrinho.verificaQtdeItens(item));
    }

    @Test
    public void removerItemComCarrinhode2ItensDiferentes() throws ModeloInvalidoException {
        Item item = new Item("1", "ITEM TESTE", 100d);
        carrinho.adicionarItem(item);
        Item outroItem = new Item("2", "OUTRO ITEM TESTE", 222d);
        carrinho.adicionarItem(outroItem);
        assertEquals(1, carrinho.verificaQtdeItens(item));
        assertEquals(1, carrinho.verificaQtdeItens(outroItem));
        carrinho.removerItem(item);
        assertEquals(0, carrinho.verificaQtdeItens(item));
        assertEquals(1, carrinho.verificaQtdeItens(outroItem));
        carrinho.removerItem(outroItem);
        assertEquals(0, carrinho.verificaQtdeItens(item));
        assertEquals(0, carrinho.verificaQtdeItens(outroItem));
        carrinho.removerItem(item);
    }



    @Test
    public void fecharCompras() throws IOException, ModeloInvalidoException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        Item itemA = new Item("1", "ITEM A", 100d);
        Item itemB = new Item("2", "ITEM B", 200d);
        carrinho.adicionarItem(itemB);
        carrinho.adicionarItem(itemA);
        carrinho.fecharCompras();
        bo.flush();
        String linhasEscritas = new String(bo.toByteArray());
        assertEquals("CARRINHO:\nITEM A -- R$100.0\n" +
                "ITEM B -- R$200.0\n" +
                "TOTAL: R$300.0", linhasEscritas);
    }
}