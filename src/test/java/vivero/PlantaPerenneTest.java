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
public class PlantaPerenneTest {
    
    public PlantaPerenneTest() {
    }

    /*
        Correcto funcionamiento del metodo paso
    */
    @Test
    public void paso() {
        Planta planta = new PlantaPerenne("Pino", "Hoy");
        planta.agregarEtapa(new Etapa(0, 90, 50, 40, 15, 20, 30, 10));
        planta.agregarEtapa(new Etapa(1, 90, 50, 40, 15, 20, 30, 10));
        planta.agregarEtapa(new Etapa(2, 90, 50, 40, 15, 20, 30, 10));
        planta.agregarEtapa(new Etapa(3, 90, 50, 40, 15, 20, 30, 10));
        
        assertTrue(planta.paso());
        
        assertEquals(0, planta.getEtapaActual().getOrdenTipo());
        for(int i = 0; i < 9; i++){
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
        for(int i = 0; i < 10; i++){
            planta.paso();
        }
        assertEquals(2, planta.getEtapaActual().getOrdenTipo());
        for(int i = 0; i < 10; i++){
            planta.paso();
        }
        assertEquals(3, planta.getEtapaActual().getOrdenTipo());
        
        assertFalse(planta.estaMuerta());
    }
    
}
