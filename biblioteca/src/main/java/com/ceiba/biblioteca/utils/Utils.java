package com.ceiba.biblioteca.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
  
  private Utils() {
  }
  
  public static final int USUARIO_INVITADO = 3;
  
  public static String formatearFechaMaximaDeDevolucion(LocalDate fechaMaximaDevolucion) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return fechaMaximaDevolucion.format(formatter);
  }

}
