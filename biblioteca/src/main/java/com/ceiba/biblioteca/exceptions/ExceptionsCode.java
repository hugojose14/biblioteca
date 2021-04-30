package com.ceiba.biblioteca.exceptions;

public enum ExceptionsCode {

  TIPO_USUARIO_NO_PERMITIDO_EN_BIBLIOTECA("E-001","Usuario no permitido en biblioteca"),
  USUARIO_INVITADO_MAS_DE_UN_PRESTAMO("E-002","Usurio invitado con más de un prestamo"),
  ARGUMENTO_INVALIDO("E-003", "Argumento inválido");
  
  private final String codigo;
  private final String descripcion;
  
  private ExceptionsCode(String codigo, String descripcion) {
    this.codigo = codigo;
    this.descripcion = descripcion;
  }
  
  public String getCodigo() {
    return this.codigo;
  }
  
  public String getDescripcion() {
    return this.descripcion;
  }
  
}
