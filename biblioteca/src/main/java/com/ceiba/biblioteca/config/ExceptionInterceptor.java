package com.ceiba.biblioteca.config;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.ceiba.biblioteca.exceptions.ExceptionsCode;
import com.ceiba.biblioteca.exceptions.UsuarioInvitadoMasDeUnPrestamoExcepcion;
import com.ceiba.biblioteca.infrastructure.rest.RespuestaError;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler  {
  
  @ExceptionHandler(UsuarioInvitadoMasDeUnPrestamoExcepcion.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<RespuestaError> usuarioInvitadoMasDeUnPrestamo(
      UsuarioInvitadoMasDeUnPrestamoExcepcion usuarioInvitadoMasDeUnPrestamoException) {
    
    RespuestaError errorResponse =
        new RespuestaError(usuarioInvitadoMasDeUnPrestamoException.getMessage(),
            ExceptionsCode.USUARIO_INVITADO_MAS_DE_UN_PRESTAMO.getCodigo());

    return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    List<String> errores = ex.getBindingResult().getFieldErrors().stream()
        .map(FieldError::getDefaultMessage).collect(Collectors.toList());

    RespuestaError respuestaError =
        new RespuestaError("Tipo de usuario no permitido en la biblioteca",
            ExceptionsCode.ARGUMENTO_INVALIDO.getCodigo(), errores);

    return new ResponseEntity<>(respuestaError, HttpStatus.BAD_REQUEST);

  }
}
