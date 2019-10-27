package com.altran.desafio.model;


import com.altran.desafio.exceptions.ModeloInvalidoException;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
public class Usuario {
    @Id
    private String id;
    private String nome;
    private String email;
    @ToString.Exclude
    private Carrinho carrinho;


    public void valida() throws ModeloInvalidoException {
        if(nome == null || nome.isEmpty())throw new ModeloInvalidoException("Nome deve ser informado.");
        if(email == null || email.isEmpty()) throw new ModeloInvalidoException("Email deve ser informado.");
    }

    public Carrinho abreCarrinho(){
        if(carrinho == null) carrinho = new Carrinho(this);
        return carrinho;
    }

    public void fechaCarrinho(){
        carrinho = null;
    }
}
