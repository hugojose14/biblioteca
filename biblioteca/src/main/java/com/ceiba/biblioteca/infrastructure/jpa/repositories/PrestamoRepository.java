package com.ceiba.biblioteca.infrastructure.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ceiba.biblioteca.domain.model.SolicitudPrestarLibro;

public interface PrestamoRepository extends JpaRepository<SolicitudPrestarLibro, Long>{

  boolean existsByIdentificacionUsuario(String identificacionUsuario);
  
}
