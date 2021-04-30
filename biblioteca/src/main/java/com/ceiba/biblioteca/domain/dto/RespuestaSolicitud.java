package com.ceiba.biblioteca.domain.dto;

public class RespuestaSolicitud {

  private long id;
  private String fechaMaximaDevolucion;
  
  public RespuestaSolicitud() {}
  
  public long  getId() {
    return this.id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public String  getFechaMaximaDevolucion() {
    return this.fechaMaximaDevolucion;
  }
  
  public void setFechaMaximaDevolucion(String fechaMaximaDevolucion) {
    this.fechaMaximaDevolucion = fechaMaximaDevolucion;
  }
  
}
