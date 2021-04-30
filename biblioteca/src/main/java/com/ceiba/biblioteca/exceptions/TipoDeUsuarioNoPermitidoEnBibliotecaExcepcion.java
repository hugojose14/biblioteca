package com.ceiba.biblioteca.exceptions;

public class TipoDeUsuarioNoPermitidoEnBibliotecaExcepcion extends RuntimeException {
  
  private static final long serialVersionUID = -9009225222834428536L;

  public TipoDeUsuarioNoPermitidoEnBibliotecaExcepcion(String mensaje) {
    super(mensaje);
  }

}
