package com.altran.desafio.controller;

import com.altran.desafio.exceptions.DesafioException;
import com.altran.desafio.exceptions.ModeloInvalidoException;
import com.altran.desafio.model.Usuario;
import com.altran.desafio.repos.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UsuarioController {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public void save(Usuario usuario){
        try {
            usuario.valida();
        }catch (NullPointerException | ModeloInvalidoException exception){
            throw new DesafioException("O usuario nao e valido.");
        }
        Usuario outro = usuarioRepository.findByEmail(usuario.getEmail());
        if(outro != null && !outro.equals(usuario)){
            throw new DesafioException("Ja existe usuario com este e-mail");
        }
        usuarioRepository.save(usuario);
    }
    public void delete(Usuario usuario){
        usuarioRepository.delete(usuario);
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(String id) {
        return usuarioRepository.findById(id).get();
    }


}
