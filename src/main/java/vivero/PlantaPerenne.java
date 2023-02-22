/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vivero;

import org.json.JSONObject;

/**
 * Subclase de Planta, que modela una planta perenne (la cual posee un ciclo de vida en el cual las etapas de vegetatividad y reproduccion se repiten reiteradas veces).
 * Al ser perenne tambien incluye un contador de reproducciones (cantidad de veces que se paso por una etapa de Reproduccion).
 * @author Luciano Peschiutta
 */
public class PlantaPerenne extends Planta{
    
    protected int cantidadReproducciones;

    /**
     * Constructor de la clase, se inicia como viva, ademas los datos de humedad, temperatura y luminosidad inician en -1 hasta que sean actualizados.
     * @param nombre Nombre de la Planta 
     * @param fechaPlantado Fecha de plantado de la Planta
     */
    public PlantaPerenne(String nombre, String fechaPlantado) {
        super(nombre, fechaPlantado);
        tipo = "Perenne";
        cantidadReproducciones = 0;
    }
    
    /**
     * Metodo que incrementa el progreso de la etapa actual en 1
     * Al ser una planta perenne las etapas del tipo 2 y 3 se vuelven a colocar al final de la cola de etapas al finalizar
     * Al final de cada etapa de Reproduccion se incrementa el contador de reproducciones
     * @return True si el progreso se incremento correctamente, False si no fue asi (la planta esta muerta)
     */
    @Override
    public boolean paso(){
        
        if(!etapas.isEmpty() && !flagMuerta){
        
            if(!etapas.get(0).paso()){
                if(etapas.get(0).getOrdenTipo() > 1){
                    etapas.add(new Etapa(etapas.get(0)));
                    if(etapas.get(0).getOrdenTipo() == 3){
                        cantidadReproducciones++;
                    }
                }
                etapas.remove(0);
            }
            
            return true;
            
        }else{
            
            return false;
            
        }
  
    }
    
    /**
     * Retorna los datos de la planta
     * @return JSONObject con los datos y estado de la planta
     * El JSON contiene: Nombre, Tipo, Humedad, Temperatura, Luminosidad, FechaPlantado, Muerta, CantidadReproducciones.
     * Si la planta contiene etapas, entonces incluira los siguientes datos de la etapa actual: Etapa, hMax, hMin, tMax, tMin, lMax, lMin, Duracion, Progreso.
     */
    @Override
    public JSONObject getEstado(){
        
        JSONObject JS = super.getEstado();
        JS.put("CantidadReproducciones", cantidadReproducciones);
        return JS;
        
    }
    
}
