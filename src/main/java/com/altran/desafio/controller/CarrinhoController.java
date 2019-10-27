package com.altran.desafio.controller;

import com.altran.desafio.exceptions.DesafioException;
import com.altran.desafio.exceptions.ModeloInvalidoException;
import com.altran.desafio.model.Carrinho;
import com.altran.desafio.model.Usuario;
import com.altran.desafio.repos.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CarrinhoController {

    @Autowired
    private CarrinhoRepository repository;

    public void save(Carrinho carrinho){
        try {
            carrinho.valida();
        } catch (NullPointerException | ModeloInvalidoException e) {
            throw new DesafioException("O carrinho nao e valido.");
        }
        repository.save(carrinho);
    }

    public void delete(Carrinho carrinho){
        repository.delete(carrinho);
    }

    public List<Carrinho> findAll(){
        return repository.findAllByOrderByTotal();
    }

    public Carrinho findByUsuario(Usuario usuario){
        if(usuario == null || usuario.getId() == null){
            throw new DesafioException("O usuario deve ser informado.");
        }
        return repository.findByUsuario(usuario);
    }


}
