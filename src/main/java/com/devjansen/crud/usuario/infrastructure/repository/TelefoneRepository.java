package com.devjansen.crud.usuario.infrastructure.repository;

import com.devjansen.crud.usuario.infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
