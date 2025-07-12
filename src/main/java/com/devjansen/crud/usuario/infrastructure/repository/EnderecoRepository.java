package com.devjansen.crud.usuario.infrastructure.repository;

import com.devjansen.crud.usuario.infrastructure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
