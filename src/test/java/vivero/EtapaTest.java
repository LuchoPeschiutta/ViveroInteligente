/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package vivero;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author pesch
 */
public class EtapaTest {
    

    
    public EtapaTest() {
    }

    @Test
    public void testGerminacion() {
        Etapa etapa = new Etapa(0, 90, 50, 40, 15, 20, 30, 10);
        assertEquals("Germinacion", etapa.getNombreTipo(), "No se genera etapa tipo Germinacion");
    }
    
    @Test
    public void testCrecimiento() {
        Etapa etapa = new Etapa(1, 90, 50, 40, 15, 20, 30, 10);
        assertEquals("Crecimiento", etapa.getNombreTipo(), "No se genera etapa tipo Crecimiento");
    }
    
    @Test
    public void testVegetativa() {
        Etapa etapa = new Etapa(2, 90, 50, 40, 15, 20, 30, 10);
        assertEquals("Vegetativa", etapa.getNombreTipo(), "No se genera etapa tipo Vegetativa");
    }
    
    @Test
    public void testReproduccion() {
        Etapa etapa = new Etapa(3, 90, 50, 40, 15, 20, 30, 10);
        assertEquals("Reproduccion", etapa.getNombreTipo(), "No se genera etapa tipo Reproduccion");
    }
    
    @Test
    public void testTipoInvalido() {
        Etapa etapa;
        try{
            etapa = new Etapa(4, 90, 50, 40, 15, 20, 30, 10);
        }
        catch(Exception e){
            etapa = null;
        }
        assertNull(etapa, "Se genera un objeto que no deberia existir");
    }
    
    @Test
    public void testPaso(){
        Etapa etapa = new Etapa(3, 90, 50, 40, 15, 20, 30, 10);
        assertEquals(0, etapa.getDuracionActual(), "No se inicia duracionActual en 0");
        assertTrue(etapa.paso(), "Avanzar sin que la etapa termine no retorna true");
        for(int i=0; i<8; i++){
            etapa.paso();
        }
        assertEquals(9, etapa.getDuracionActual(), "No se cuenta correctamente el progreso de duracionActual");
        assertFalse(etapa.paso(), "No se retorna false al finalizar la etapa");
    }
    
    @Test
    public void testCopia(){
        Etapa etapa = new Etapa(3, 90, 50, 40, 15, 20, 30, 10);
        etapa.paso();
        Etapa etapa2 = new Etapa(etapa);
        assertEquals(etapa.getHumedad()[0], etapa2.getHumedad()[0]);
        assertEquals(etapa.getHumedad()[1], etapa2.getHumedad()[1]);
        assertEquals(etapa.getTemperatura()[0], etapa2.getTemperatura()[0]);
        assertEquals(etapa.getTemperatura()[1], etapa2.getTemperatura()[1]);
        assertEquals(etapa.getLuminosidad()[0], etapa2.getLuminosidad()[0]);
        assertEquals(etapa.getLuminosidad()[1], etapa2.getLuminosidad()[1]);
        assertEquals(etapa.getNombreTipo(), etapa2.getNombreTipo());
        assertEquals(etapa.getDuracionLimite(), etapa2.getDuracionLimite());
        assertNotEquals(etapa.getDuracionActual(), etapa2.getDuracionActual());
        
    }
}
