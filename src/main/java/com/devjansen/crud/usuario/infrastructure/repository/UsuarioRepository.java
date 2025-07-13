package com.devjansen.crud.usuario.infrastructure.repository;

import com.devjansen.crud.usuario.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

}
