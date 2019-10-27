package com.altran.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarrinho {
    private Item item;
    private int quantidade = 0;

    public void incrmentaQuantidade(){
        quantidade++;
    }


    public void decrementaQuantidade() {
        if(quantidade > 0){
            quantidade--;
        }
    }
}
