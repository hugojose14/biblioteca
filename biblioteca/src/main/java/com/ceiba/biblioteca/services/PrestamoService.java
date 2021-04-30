package com.ceiba.biblioteca.services;

import java.util.Optional;
import com.ceiba.biblioteca.domain.dto.RespuestaPrestamo;
import com.ceiba.biblioteca.domain.dto.RespuestaSolicitud;
import com.ceiba.biblioteca.domain.dto.SolicitudPrestarLibroDto;

public interface PrestamoService {
  RespuestaSolicitud crearPrestamo(SolicitudPrestarLibroDto solicitudPrestarLibroDto);
  Optional<RespuestaPrestamo> obtenerPrestamoPorId(Long id);
}
