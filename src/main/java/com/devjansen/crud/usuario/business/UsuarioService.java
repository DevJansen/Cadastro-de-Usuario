package com.devjansen.crud.usuario.business;

import com.devjansen.crud.usuario.infrastructure.entity.Usuario;
import com.devjansen.crud.usuario.infrastructure.exceptions.ConflictException;
import com.devjansen.crud.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.devjansen.crud.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario salvaUsuario(Usuario usuario){
        emailExiste(usuario.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
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

    public Usuario buscarUsuario(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email não encontrado " + email));
    }

    public void  deletaUsuario(String email){
        usuarioRepository.deleteByEmail(email);

    }
}
