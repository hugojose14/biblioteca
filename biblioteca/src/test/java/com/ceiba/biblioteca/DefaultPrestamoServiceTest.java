package com.ceiba.biblioteca;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ceiba.biblioteca.domain.dto.RespuestaPrestamo;
import com.ceiba.biblioteca.domain.dto.RespuestaSolicitud;
import com.ceiba.biblioteca.domain.dto.SolicitudPrestarLibroDto;
import com.ceiba.biblioteca.domain.model.SolicitudPrestarLibro;
import com.ceiba.biblioteca.infrastructure.jpa.repositories.PrestamoRepository;
import com.ceiba.biblioteca.services.internal.DefaultPrestamoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DefaultPrestamoServiceTest {
  
  @Mock
  private PrestamoRepository prestamoRepository;
  
  @InjectMocks
  DefaultPrestamoService defaultPrestamoService;
  
  @Test
  public void getPrestamoTest() {
    
    SolicitudPrestarLibro solicitudPrestarLibro = new SolicitudPrestarLibro();
    solicitudPrestarLibro.setId(1L);
    solicitudPrestarLibro.setFechaMaximaDevolucion(LocalDate.now());
    solicitudPrestarLibro.setIdentificacionUsuario("10012912");
    solicitudPrestarLibro.setIsbn("ASA9232");
    solicitudPrestarLibro.setTipoUsuario(1);
    
    Optional<SolicitudPrestarLibro> solicitudPrestarLibroOptional =
        Optional.of(solicitudPrestarLibro);
    
    when(prestamoRepository.findById(1L)).thenReturn(solicitudPrestarLibroOptional);
    
    Optional<RespuestaPrestamo> solicitudPrestarLibroResponse =
        defaultPrestamoService.obtenerPrestamoPorId(1L);
    
    assertEquals(solicitudPrestarLibroOptional.get().getId(),
        solicitudPrestarLibroResponse.get().getId());
    
    assertEquals(solicitudPrestarLibroOptional.get().getIdentificacionUsuario(),
        solicitudPrestarLibroResponse.get().getIdentificacionUsuario());
        
  }
  
  @Test
  public void getPrestamoTestNoPresent() {
        
    when(prestamoRepository.findById(1L)).thenReturn(Optional.empty());
    
    Optional<RespuestaPrestamo> solicitudPrestarLibroResponse =
        defaultPrestamoService.obtenerPrestamoPorId(1L);
    
    assertEquals(Optional.empty(), solicitudPrestarLibroResponse);
        
  }
  
  @Test
  public void crearPrestamoNoExisteUsuarioYRegistrar() {
    SolicitudPrestarLibroDto solicitudPrestarLibroDto = new SolicitudPrestarLibroDto();
    solicitudPrestarLibroDto.setIdentificacionUsuario("11111111");
    solicitudPrestarLibroDto.setIsbn("ASK12");
    solicitudPrestarLibroDto.setTipoUsuario(1);
    
    SolicitudPrestarLibro solicitudPrestarLibro = new SolicitudPrestarLibro();
    solicitudPrestarLibro.setIdentificacionUsuario("11111111");
    solicitudPrestarLibro.setIsbn("ASK12");
    solicitudPrestarLibro.setTipoUsuario(1);
    solicitudPrestarLibro.setId(1L);
    solicitudPrestarLibro.setFechaMaximaDevolucion(LocalDate.now());
    
    RespuestaSolicitud respuestaSolicitud = new RespuestaSolicitud();
    respuestaSolicitud.setFechaMaximaDevolucion(LocalDate.now().toString());
    respuestaSolicitud.setId(1L);
    
    when(prestamoRepository.existsByIdentificacionUsuario("11111111")).thenReturn(false);
    when(prestamoRepository.save(Mockito.any(SolicitudPrestarLibro.class))).thenReturn(solicitudPrestarLibro);
    when(defaultPrestamoService.crearPrestamo(solicitudPrestarLibroDto)).thenReturn(respuestaSolicitud);

    RespuestaSolicitud respuestaSolicitudEsperada  =  defaultPrestamoService.crearPrestamo(solicitudPrestarLibroDto);
    
    assertNotNull(respuestaSolicitudEsperada);
    assertEquals(respuestaSolicitud.getId(), respuestaSolicitudEsperada.getId());
   
  }
  
  @Test
  public void crearPrestamoExisteUsuarioYRegistrarDiferenteAInvitado() {
    SolicitudPrestarLibroDto solicitudPrestarLibroDto = new SolicitudPrestarLibroDto();
    solicitudPrestarLibroDto.setIdentificacionUsuario("222222");
    solicitudPrestarLibroDto.setIsbn("ASK12");
    solicitudPrestarLibroDto.setTipoUsuario(2);
    
    SolicitudPrestarLibro solicitudPrestarLibro = new SolicitudPrestarLibro();
    solicitudPrestarLibro.setIdentificacionUsuario("222222");
    solicitudPrestarLibro.setIsbn("ASK12");
    solicitudPrestarLibro.setTipoUsuario(2);
    solicitudPrestarLibro.setId(2L);
    solicitudPrestarLibro.setFechaMaximaDevolucion(LocalDate.now());
    
    RespuestaSolicitud respuestaSolicitud = new RespuestaSolicitud();
    respuestaSolicitud.setFechaMaximaDevolucion(LocalDate.now().toString());
    respuestaSolicitud.setId(2L);
    
    when(prestamoRepository.existsByIdentificacionUsuario("11111111")).thenReturn(true);
    when(prestamoRepository.save(Mockito.any(SolicitudPrestarLibro.class))).thenReturn(solicitudPrestarLibro);
    when(defaultPrestamoService.crearPrestamo(solicitudPrestarLibroDto)).thenReturn(respuestaSolicitud);

    RespuestaSolicitud respuestaSolicitudEsperada  =  defaultPrestamoService.crearPrestamo(solicitudPrestarLibroDto);
    
    assertNotNull(respuestaSolicitudEsperada);
    assertEquals(respuestaSolicitud.getId(), respuestaSolicitudEsperada.getId());
   
  }
  
  @Test
  public void crearPrestamoExisteUsuarioYRegistrarDiferenteAInvitadoYEsDeTipo2() {
    SolicitudPrestarLibroDto solicitudPrestarLibroDto = new SolicitudPrestarLibroDto();
    solicitudPrestarLibroDto.setIdentificacionUsuario("222222");
    solicitudPrestarLibroDto.setIsbn("ASK12");
    solicitudPrestarLibroDto.setTipoUsuario(2);
    
    SolicitudPrestarLibro solicitudPrestarLibro = new SolicitudPrestarLibro();
    solicitudPrestarLibro.setIdentificacionUsuario("222222");
    solicitudPrestarLibro.setIsbn("ASK12");
    solicitudPrestarLibro.setTipoUsuario(2);
    solicitudPrestarLibro.setId(2L);
    solicitudPrestarLibro.setFechaMaximaDevolucion(LocalDate.now());
    
    RespuestaSolicitud respuestaSolicitud = new RespuestaSolicitud();
    respuestaSolicitud.setFechaMaximaDevolucion(LocalDate.now().toString());
    respuestaSolicitud.setId(2L);
    
    when(prestamoRepository.existsByIdentificacionUsuario("11111111")).thenReturn(true);
    when(prestamoRepository.save(Mockito.any(SolicitudPrestarLibro.class))).thenReturn(solicitudPrestarLibro);
    when(defaultPrestamoService.crearPrestamo(solicitudPrestarLibroDto)).thenReturn(respuestaSolicitud);

    RespuestaSolicitud respuestaSolicitudEsperada = defaultPrestamoService.crearPrestamo(solicitudPrestarLibroDto);
    
    assertNotNull(respuestaSolicitudEsperada);
    assertEquals(respuestaSolicitud.getId(), respuestaSolicitudEsperada.getId());
   
  }
  
}
