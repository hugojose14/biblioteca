package com.ceiba.biblioteca.assemblers;

import java.util.Objects;
import java.util.logging.Logger;
import com.ceiba.biblioteca.domain.dto.RespuestaPrestamo;
import com.ceiba.biblioteca.domain.model.SolicitudPrestarLibro;
import com.ceiba.biblioteca.exceptions.ObjetoNuloExcepcion;
import com.ceiba.biblioteca.utils.Utils;

public class PrestamoAssembler {
  
  private static final Logger LOGGER = Logger.getLogger(PrestamoAssembler.class.getName());
  
  private PrestamoAssembler() {
    
  }
  
  public static RespuestaPrestamo convertirSolicitudPrestarLibroARespuestaPrestamo(
      SolicitudPrestarLibro solicitudPrestarLibro) {
    
    RespuestaPrestamo respuestaPrestamo = new RespuestaPrestamo();
    
    if(Objects.isNull(solicitudPrestarLibro)) {
      throw new ObjetoNuloExcepcion("el objeto no puede ser null");
    }
    
    try {
        respuestaPrestamo.setId(solicitudPrestarLibro.getId());
        respuestaPrestamo.setFechaMaximaDevolucion(
            Utils.formatearFechaMaximaDeDevolucion(solicitudPrestarLibro.getFechaMaximaDevolucion()));
        respuestaPrestamo.setIdentificacionUsuario(solicitudPrestarLibro.getIdentificacionUsuario());
        respuestaPrestamo.setIsbn(solicitudPrestarLibro.getIsbn());
        respuestaPrestamo.setTipoUsuario(solicitudPrestarLibro.getTipoUsuario());
        return respuestaPrestamo; 
    } catch (Exception e) {
      LOGGER.log(null, "convertirSolicitudPrestarLibroARespuestaPrestamo {}",e.getMessage());
    }
    return respuestaPrestamo;
    
  }
  

}
