package com.ceiba.biblioteca.domain.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prestamos")
public class SolicitudPrestarLibro {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @Column(name = "isbn", columnDefinition = "VARCHAR(10)")
  private String isbn;
  @Column(name = "identificacionUsuario", columnDefinition = "VARCHAR(10)")
  private String identificacionUsuario;
  @Column(name = "tipoUsuario")
  private int tipoUsuario;
  private LocalDate fechaMaximaDevolucion;
  
  public SolicitudPrestarLibro() {
    
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
  
  public LocalDate getFechaMaximaDevolucion() {
    return this.fechaMaximaDevolucion;
  }
  
  public void setFechaMaximaDevolucion(LocalDate fechaMaximaDevolucion) {
    this.fechaMaximaDevolucion = fechaMaximaDevolucion;
  }
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  

}
