package com.altran.desafio.model;

import com.altran.desafio.exceptions.ModeloInvalidoException;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString(exclude = "usuario")
@Data
public class Carrinho {
    @Id
    private String id;
    @DBRef(lazy = true)
    private Usuario usuario;
    private Double total;
    private List<ItemCarrinho> items;

    public Carrinho(Usuario usuario){
        this.usuario =usuario;
        items = new ArrayList<>();
        total = 0d;
    }

    public void adicionarItem(Item item) throws ModeloInvalidoException {
        item.valida();
        boolean achou = false;
        for(ItemCarrinho ic : items){
            if(ic.getItem().equals(item)){
                ic.incrmentaQuantidade();
                achou = true;
            }
        }
        if(!achou){
            items.add(new ItemCarrinho(item, 1));
        }
        total += item.getValor();
    }

    public void removerItem(Item item){
        for(ItemCarrinho ic : items){
            if(ic.getItem().equals(item)){
                ic.decrementaQuantidade();
                total -= item.getValor();
            }
        }
    }

    public int verificaQtdeItens(Item item){
        int qtde = 0;
        for(ItemCarrinho ic : items){
            if(ic.getItem().equals(item)){
                qtde = ic.getQuantidade();
            }
        }
        return qtde;
    }

    public void fecharCompras(){
        Collections.sort(items, (o1, o2) -> o1.getItem().getNome().compareTo(o2.getItem().getNome()));
        System.out.print("CARRINHO:\n");
        for (ItemCarrinho itemCarrinho : items){
            System.out.print(itemCarrinho.getItem().getNome()+ " -- R$" +itemCarrinho.getItem().getValor()+"\n");
        }
        System.out.print("TOTAL: R$"+total);

    }

    public void valida() throws ModeloInvalidoException {
        if(usuario == null) throw new ModeloInvalidoException("O carrinho deve estar associado a algum usuario");

    }
}
