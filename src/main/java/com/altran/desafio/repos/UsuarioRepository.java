package com.altran.desafio.repos;

import com.altran.desafio.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findByEmail(String email);
}
