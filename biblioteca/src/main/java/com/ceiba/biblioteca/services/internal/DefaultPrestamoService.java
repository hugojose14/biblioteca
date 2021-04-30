package com.ceiba.biblioteca.services.internal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ceiba.biblioteca.assemblers.PrestamoAssembler;
import com.ceiba.biblioteca.domain.dto.RespuestaPrestamo;
import com.ceiba.biblioteca.domain.dto.RespuestaSolicitud;
import com.ceiba.biblioteca.domain.dto.SolicitudPrestarLibroDto;
import com.ceiba.biblioteca.domain.model.SolicitudPrestarLibro;
import com.ceiba.biblioteca.exceptions.UsuarioInvitadoMasDeUnPrestamoExcepcion;
import com.ceiba.biblioteca.infrastructure.jpa.repositories.PrestamoRepository;
import com.ceiba.biblioteca.services.PrestamoService;
import com.ceiba.biblioteca.utils.Utils;

@Service
public class DefaultPrestamoService implements PrestamoService {

  private final PrestamoRepository prestamoRepository;
  
   public DefaultPrestamoService(PrestamoRepository prestamoRepository) {
    this.prestamoRepository = prestamoRepository;
  }
  
  @Override
  public RespuestaSolicitud crearPrestamo(SolicitudPrestarLibroDto solicitudPrestarLibroDto) {
    
    SolicitudPrestarLibro solicitudPrestarLibro = new SolicitudPrestarLibro();
    RespuestaSolicitud respuestaSolicitud = new RespuestaSolicitud();
    
    if(!prestamoRepository.existsByIdentificacionUsuario(solicitudPrestarLibroDto.getIdentificacionUsuario())) {
      return setearInformacionSolicitudPrestarLibroYGuardarSolicitud(solicitudPrestarLibroDto, solicitudPrestarLibro,
          respuestaSolicitud);
    }
    
    else if(prestamoRepository.existsByIdentificacionUsuario(solicitudPrestarLibroDto.getIdentificacionUsuario())
        && solicitudPrestarLibroDto.getTipoUsuario() == Utils.USUARIO_INVITADO
        ) {
          throw new UsuarioInvitadoMasDeUnPrestamoExcepcion(new StringBuilder().append(
              "El usuario con identificación ").append(solicitudPrestarLibroDto.getIdentificacionUsuario())
              .append(" ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo")
              .toString());
        }
    
    else if(prestamoRepository.existsByIdentificacionUsuario(solicitudPrestarLibroDto.getIdentificacionUsuario())
        && solicitudPrestarLibroDto.getTipoUsuario() != Utils.USUARIO_INVITADO) {
      return setearInformacionSolicitudPrestarLibroYGuardarSolicitud(solicitudPrestarLibroDto, solicitudPrestarLibro,
          respuestaSolicitud);
    }
    
    return respuestaSolicitud;
  }
  
  @Override
  public Optional<RespuestaPrestamo> obtenerPrestamoPorId(Long id) {
    
    Optional<SolicitudPrestarLibro> solicitudPrestarLibro = prestamoRepository.findById(id);
    
    if(solicitudPrestarLibro.isPresent()) {
      return Optional.of(PrestamoAssembler.convertirSolicitudPrestarLibroARespuestaPrestamo(solicitudPrestarLibro.get()));
    } 
    return Optional.empty();
  }

  private RespuestaSolicitud setearInformacionSolicitudPrestarLibroYGuardarSolicitud(
      SolicitudPrestarLibroDto solicitudPrestarLibroDto,
      SolicitudPrestarLibro solicitudPrestarLibro, RespuestaSolicitud respuestaSolicitud) {
    solicitudPrestarLibro.setFechaMaximaDevolucion(asignarFechaDevolucionDeAcuerdoAlTipoDeUsuario(solicitudPrestarLibroDto.getTipoUsuario()));
    solicitudPrestarLibro.setIdentificacionUsuario(solicitudPrestarLibroDto.getIdentificacionUsuario());
    solicitudPrestarLibro.setIsbn(solicitudPrestarLibroDto.getIsbn());
    solicitudPrestarLibro.setTipoUsuario(solicitudPrestarLibroDto.getTipoUsuario());
    SolicitudPrestarLibro solicitarPrestarLibroEntity = prestamoRepository.save(solicitudPrestarLibro);
    respuestaSolicitud.setFechaMaximaDevolucion(Utils.formatearFechaMaximaDeDevolucion(solicitarPrestarLibroEntity.getFechaMaximaDevolucion()));
    respuestaSolicitud.setId(solicitarPrestarLibroEntity.getId());
    return respuestaSolicitud;
  }
  
  public LocalDate asignarFechaDevolucionDeAcuerdoAlTipoDeUsuario(int tipoUsuario) {
    
    LocalDate fechaMaximaDevolucion = LocalDate.now();
    int diasAgregados = 0;
    int diasAgregadosAfiliado = 10;
    int diasAgregadosEmpleadoBiblioteca = 8;
    int diasAgregadosInvitado = 7;
    
    switch (tipoUsuario) {
      case 1:
        return calcularDiasTotalFechaMaximaDevolucion(fechaMaximaDevolucion, diasAgregados,
            diasAgregadosAfiliado);
      case 2:
        return calcularDiasTotalFechaMaximaDevolucion(fechaMaximaDevolucion, diasAgregados,
            diasAgregadosEmpleadoBiblioteca);        
      case 3:
        return calcularDiasTotalFechaMaximaDevolucion(fechaMaximaDevolucion, diasAgregados,
            diasAgregadosInvitado);   
      default:
        return fechaMaximaDevolucion;
    }
    
  }

  private LocalDate calcularDiasTotalFechaMaximaDevolucion(LocalDate fechaMaximaDevolucion,
      int diasAgregados, int diasAgregadosAfiliado) {
    while (diasAgregados < diasAgregadosAfiliado) {
      fechaMaximaDevolucion = fechaMaximaDevolucion.plusDays(1);
      if (!(fechaMaximaDevolucion.getDayOfWeek() == DayOfWeek.SATURDAY
          || fechaMaximaDevolucion.getDayOfWeek() == DayOfWeek.SUNDAY)) {
        ++diasAgregados;
      }
    }
    return fechaMaximaDevolucion;
  }

}
