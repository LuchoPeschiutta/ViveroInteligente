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
public class PlantaNoPerenneTest {
    
    public PlantaNoPerenneTest() {
    }

    /*
        Correcto funcionamiento del metodo paso
    */
    @Test
    public void paso() {
        
        Planta planta = new PlantaNoPerenne("Maiz", "Hoy");
        planta.agregarEtapa(new Etapa(0, 90, 50, 40, 15, 20, 30, 10));
        planta.agregarEtapa(new Etapa(1, 90, 50, 40, 15, 20, 30, 10));
        planta.agregarEtapa(new Etapa(2, 90, 50, 40, 15, 20, 30, 10));
        planta.agregarEtapa(new Etapa(3, 90, 50, 40, 15, 20, 30, 10));
        
        assertEquals(0, planta.getEtapaActual().getOrdenTipo());
        for(int i = 0; i < 10; i++){
            planta.paso();
        }
        assertEquals(1, planta.getEtapaActual().getOrdenTipo());
        for(int i = 0; i < 10; i++){
            planta.paso();
        }
        assertEquals(2, planta.getEtapaActual().getOrdenTipo());
        for(int i = 0; i < 10; i++){
            planta.paso();
        }
        assertEquals(3, planta.getEtapaActual().getOrdenTipo());
        for(int i = 0; i < 9; i++){
            planta.paso();
        }
        
        assertFalse(planta.estaMuerta());
        assertTrue(planta.paso());
        assertTrue(planta.estaMuerta());
        assertFalse(planta.paso());
    }
    
    /*
        Correcto funcionamiento del metodo get estado
    */
    @Test
    public void getEstado(){
        
        Planta planta = new PlantaNoPerenne("Maiz", "Hoy");
        planta.agregarEtapa(new Etapa(0, 90, 50, 40, 15, 20, 30, 10));
        
        JSONObject estado = planta.getEstado();
        
        assertEquals("Maiz", estado.getString("Nombre"));
        assertEquals("NoPerenne", estado.getString("Tipo"));
        assertEquals(-1, estado.getDouble("Humedad"));
        assertEquals(-1, estado.getDouble("Temperatura"));
        assertEquals(-1, estado.getDouble("Luminosidad"));
        assertEquals("Hoy", estado.getString("FechaPlantado"));
        assertEquals(false, estado.getBoolean("Muerta"));
        
        assertEquals("Germinacion", estado.get("Etapa"));
        assertEquals(90, estado.getDouble("hMax"));
        assertEquals(50, estado.getDouble("hMin"));
        assertEquals(40, estado.getDouble("tMax"));
        assertEquals(15, estado.getDouble("tMin"));
        assertEquals(20, estado.getDouble("lMax"));
        assertEquals(30, estado.getDouble("lMin"));
        assertEquals(10, estado.getInt("Duracion"));
        assertEquals(1, estado.getInt("Progreso"));
        
        for(int i=0; i<9; i++){
            planta.paso();
            estado = planta.getEstado();
            assertEquals(i+2, estado.getInt("Progreso"));
        }
        
        planta.paso();
        estado = planta.getEstado();
        assertEquals(true, estado.getBoolean("Muerta"));
        
    }
}
