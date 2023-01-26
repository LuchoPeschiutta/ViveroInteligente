/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package vivero;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author pesch
 */
public class ViveroTest {
    
    public ViveroTest() {
        
    }

    @Test
    public void TestAgregarYEliminarPlanta() {
        
        Vivero vivero = new Vivero();
        
        assertEquals(0, vivero.getUbicacionesOcupadas().size());
        assertTrue(vivero.agregarPlanta("Pino", 0));
        assertEquals(1, vivero.getUbicacionesOcupadas().size());
        assertTrue(vivero.agregarPlanta("Rosa", 1));
        assertEquals(2, vivero.getUbicacionesOcupadas().size());
        assertFalse(vivero.agregarPlanta("Pino", 0));
        assertEquals(2, vivero.getUbicacionesOcupadas().size());
        
        assertFalse(vivero.eliminarPlanta(3));
        assertTrue(vivero.eliminarPlanta(1));
        assertEquals(1, vivero.getUbicacionesOcupadas().size());
        
        
    }
    
    @Test
    public void TestGetPlanta() {
        
        Vivero vivero = new Vivero();
        
        vivero.agregarPlanta("Pino", 0);
        
        assertEquals("Pino", vivero.getPlanta(0).getNombre());
        
        vivero.agregarPlanta("Maiz", 1);
        
        assertEquals("Maiz", vivero.getPlanta(1).getNombre());
        
        assertNull(vivero.getPlanta(5));
        
        
    }
    
    @Test
    public void TestActualizarPlanta() {
        
        Vivero vivero = new Vivero();
        
        vivero.agregarPlanta("Pino", 0);
        vivero.agregarPlanta("Pino", 1);
        vivero.agregarPlanta("Pino", 2);
        
        assertTrue(vivero.actualizarPlanta(1, 20, 24, 50));
        assertFalse(vivero.actualizarPlanta(10, 20, 24, 50));
        
        JSONObject JS = vivero.getEstadoPlanta(1);
        
        assertEquals(20, JS.getDouble("Humedad"));
        assertEquals(24, JS.getDouble("Temperatura"));
        assertEquals(50, JS.getDouble("Luminosidad"));
        
        JS = vivero.getEstadoPlanta(0);
        
        assertEquals(-1, JS.getDouble("Humedad"));
        
    }
    
    @Test
    public void TestAvanzarPaso(){
        
        Vivero vivero = new Vivero();
        
        vivero.avanzarPaso();
        
        vivero.agregarPlanta("Pino", 0);
        
        for(int i=0; i<2; i++){
            vivero.avanzarPaso();
        }
        
        assertEquals(2,vivero.getPlanta(0).getEtapaActual().getDuracionActual());
        
        vivero.agregarPlanta("Rosa", 1);
        vivero.agregarPlanta("Limon", 2);
        
        for(int i=0; i<2; i++){
            vivero.avanzarPaso();
        }
        
        assertEquals(4,vivero.getPlanta(0).getEtapaActual().getDuracionActual());
        assertEquals(2,vivero.getPlanta(1).getEtapaActual().getDuracionActual());
        assertEquals(2,vivero.getPlanta(2).getEtapaActual().getDuracionActual());
        
        
    }
    
}
