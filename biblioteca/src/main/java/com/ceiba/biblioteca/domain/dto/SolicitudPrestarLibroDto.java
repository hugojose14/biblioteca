package com.ceiba.biblioteca.domain.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitudPrestarLibroDto {
  
  @NotBlank(message = "El campo isbn es requerido")
  @Size(min = 1, max = 10, message = "El campo isbn supera los caracteres permitidos")
  @JsonProperty("isbn")
  private String isbn;
  
  @NotBlank(message = "El campo de identificacionUsuario es requerido")
  @Size(min = 1, max = 10, message = "El campo de identificacionUsuario supera los caracteres permitidos")
  @JsonProperty("identificacionUsuario")
  private String identificacionUsuario;
  
  @Positive
  @Min(value = 1, message = "El tipo de usuario debe estár entre 1 - 3")
  @Max(value = 3, message = "El tipo de usuario debe estár entre 1 - 3")
  @JsonProperty("tipoUsuario")
  private int tipoUsuario;

  public SolicitudPrestarLibroDto() {}
  
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
  
}
