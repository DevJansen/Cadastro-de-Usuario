package com.devjansen.crud.usuario.business;

import com.devjansen.crud.usuario.infrastructure.entity.Usuario;
import com.devjansen.crud.usuario.infrastructure.exceptions.ConflictException;
import com.devjansen.crud.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvaUsuario(Usuario usuario){
        emailExiste(usuario.getEmail());
        return usuarioRepository.save(usuario);
    }

    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void emailExiste(String email){
        if(verificaEmailExistente(email)){
            throw new ConflictException("Email já cadastrado: " + email);
        }
    }
}
