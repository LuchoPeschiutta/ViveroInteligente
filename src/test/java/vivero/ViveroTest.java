/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package vivero;

import java.util.Arrays;
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
    
    /*
        Correcto funcionamiento de los metodos agregar y eliminar
    */
    @Test
    public void TestAgregarYEliminarPlanta() {
        
        Vivero vivero = new Vivero();
        
        assertEquals(0, vivero.getUbicacionesOcupadas().length);
        assertTrue(vivero.agregarPlanta("Pino", 0));
        assertEquals(1, vivero.getUbicacionesOcupadas().length);
        assertTrue(vivero.agregarPlanta("Rosa", 1));
        assertEquals(2, vivero.getUbicacionesOcupadas().length);
        assertFalse(vivero.agregarPlanta("Limon", 0));
        assertEquals(2, vivero.getUbicacionesOcupadas().length);
        
        assertFalse(vivero.eliminarPlanta(3));
        assertTrue(vivero.eliminarPlanta(1));
        assertEquals(1, vivero.getUbicacionesOcupadas().length);
        
        
    }
    
    /*
        Correcto funcionamiento de los metodos ubicacionEstaOcupada y getUbicacionesOcupadas
    */
    @Test
    public void TestUbicacionesOcupadas(){
        
        Vivero vivero = new Vivero();
        vivero.agregarPlanta("Pino", 0);
        vivero.agregarPlanta("Pino", 15);
        vivero.agregarPlanta("Pino", 10);
        
        assertTrue(vivero.ubicacionEstaOcupada(0));
        assertTrue(vivero.ubicacionEstaOcupada(15));
        assertTrue(vivero.ubicacionEstaOcupada(10));
        assertFalse(vivero.ubicacionEstaOcupada(5));
        
        Integer[] lista = vivero.getUbicacionesOcupadas();
        
        assertEquals(3, lista.length);
        
        assertTrue(lista[0]==0);
        assertTrue(lista[1]==10);
        assertTrue(lista[2]==15);
        
    }
    
    /*
        Correcto funcionamiento del metodo actualizarPlanta
    */
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
    
    /*
        Correcto funcionamiento del metodo avanzarPaso
    */
    @Test
    public void TestAvanzarPaso(){
        
        Vivero vivero = new Vivero();
        
        vivero.avanzarPaso();
        
        vivero.agregarPlanta("Pino", 0);
        
        for(int i=0; i<2; i++){
            vivero.avanzarPaso();
        }
        
        assertEquals(3,vivero.getEstadoPlanta(0).getInt("Progreso"));
        
        vivero.agregarPlanta("Rosa", 1);
        vivero.agregarPlanta("Limon", 2);
        
        for(int i=0; i<2; i++){
            vivero.avanzarPaso();
        }
        
        assertEquals(5,vivero.getEstadoPlanta(0).getInt("Progreso"));
        assertEquals(3,vivero.getEstadoPlanta(1).getInt("Progreso"));
        assertEquals(3,vivero.getEstadoPlanta(2).getInt("Progreso"));
        
        
    }
    
}
