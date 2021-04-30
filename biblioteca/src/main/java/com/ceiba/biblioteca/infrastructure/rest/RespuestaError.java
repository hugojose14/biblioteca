package com.ceiba.biblioteca.infrastructure.rest;

import java.util.Arrays;
import java.util.List;

public class RespuestaError {

  private String mensaje;
  private List<String> errores;
  private String codigo ;
  
  public RespuestaError(String mensaje) {
    this.mensaje = mensaje;
  }
  
  public RespuestaError(String mensaje, List<String> errores) {
    this.mensaje = mensaje;
    this.errores = errores;
  }
 
  public RespuestaError(String mensaje, String codigo) {
    this.mensaje = mensaje;
    this.codigo = codigo;
  }
  
  public RespuestaError(String mensaje, String codigo, List<String> error) {
    this.mensaje = mensaje;
    this.codigo = codigo;
    this.errores = error;
  }

  public RespuestaError(String mensaje, String codigo, String error) {
    this.mensaje = mensaje;
    this.codigo = codigo;
    this.errores = Arrays.asList(error);
  }
  
  public String getMensaje() {
    return this.mensaje;
  }
  
  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }
  
  public List<String> getErrores() {
    return this.errores;
  }
  
  public void setErrores(String data) {
    this.errores = Arrays.asList(data);
  }
  
  public String getCodigo() {
    return this.codigo;
  }
  
  public void setEstado(String codigo) {
    this.codigo = codigo;
  }
}
