package com.altran.desafio.repos;

import com.altran.desafio.model.Carrinho;
import com.altran.desafio.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarrinhoRepository extends MongoRepository<Carrinho, String> {
    Carrinho findByUsuario(Usuario usuario);
    List<Carrinho> findAllByOrderByTotal();
}
