package com.ceiba.biblioteca.exceptions;

public class UsuarioInvitadoMasDeUnPrestamoExcepcion extends RuntimeException{
  
  private static final long serialVersionUID = 1483002602935301959L;
  
  public UsuarioInvitadoMasDeUnPrestamoExcepcion(String mensaje) {
    super(mensaje);
  }

}
