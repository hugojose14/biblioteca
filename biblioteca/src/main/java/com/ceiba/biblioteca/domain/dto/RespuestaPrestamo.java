package com.ceiba.biblioteca.domain.dto;

public class RespuestaPrestamo {
  
  private Long id;
  private String isbn;
  private String identificacionUsuario;
  private int tipoUsuario;
  private String fechaMaximaDevolucion;
  
  public RespuestaPrestamo() {
  }
    
  public String getIsbn() {
    return this.isbn; 
  }
  
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }
  
  public String getIdentificacionUsuario() {
    return this.identificacionUsuario; 
  }
  
  public void setIdentificacionUsuario(String identificacionUsuario) {
    this.identificacionUsuario = identificacionUsuario;
  }
  
  public int getTipoUsuario() {
    return this.tipoUsuario; 
  }
  
  public void setTipoUsuario(int tipoUsuario) {
    this.tipoUsuario = tipoUsuario;
  }
  
  public String getFechaMaximaDevolucion() {
    return this.fechaMaximaDevolucion;
  }
  
  public void setFechaMaximaDevolucion(String fechaMaximaDevolucion) {
    this.fechaMaximaDevolucion = fechaMaximaDevolucion;
  }
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
}
