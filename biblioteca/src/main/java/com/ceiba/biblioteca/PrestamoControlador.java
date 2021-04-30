package com.ceiba.biblioteca;


import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ceiba.biblioteca.domain.dto.RespuestaPrestamo;
import com.ceiba.biblioteca.domain.dto.RespuestaSolicitud;
import com.ceiba.biblioteca.domain.dto.SolicitudPrestarLibroDto;
import com.ceiba.biblioteca.services.PrestamoService;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {
  
  @Autowired
  PrestamoService prestamoService;
  
  public PrestamoControlador() {
  }
  
  @PostMapping
  public ResponseEntity<RespuestaSolicitud> crearPrestamo(
      @Valid @RequestBody SolicitudPrestarLibroDto solicitud) throws Exception {
    RespuestaSolicitud respuestaSolicitud = prestamoService.crearPrestamo(solicitud);
    return new ResponseEntity<>(respuestaSolicitud, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<RespuestaPrestamo>> obtenerPrestamos(@PathVariable Long id)
      throws Exception {
    Optional<RespuestaPrestamo> respuestaPrestamo = prestamoService.obtenerPrestamoPorId(id);
    return new ResponseEntity<>(respuestaPrestamo, HttpStatus.OK);
  }
  
}

