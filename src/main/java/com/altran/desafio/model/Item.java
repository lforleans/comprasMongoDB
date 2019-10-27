package com.altran.desafio.model;

import com.altran.desafio.exceptions.ModeloInvalidoException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.ui.Model;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    private String id;
    private String nome;
    private Double valor;

    public Item(String nome, Double valor){
        this.nome = nome;
        this.valor = valor;
    }

    public void valida() throws ModeloInvalidoException {
        if(nome == null || nome.isEmpty())
            throw new ModeloInvalidoException("O item nao e valido.");
        if(valor == null || valor.intValue() < 0){
            throw new ModeloInvalidoException("O item nao e valido.");
        }
    }
}
