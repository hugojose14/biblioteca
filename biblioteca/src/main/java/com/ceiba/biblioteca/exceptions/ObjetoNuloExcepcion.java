package com.ceiba.biblioteca.exceptions;

public class ObjetoNuloExcepcion extends RuntimeException{
  
  private static final long serialVersionUID = 7381897003401483246L;

  public ObjetoNuloExcepcion(String mensaje) {
    super(mensaje);
  }

}
