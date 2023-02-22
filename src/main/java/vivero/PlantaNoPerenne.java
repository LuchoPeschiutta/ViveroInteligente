/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vivero;

/**
 * Subclase de Planta, que modela una planta no perenne (la cual posee un ciclo de vida en el cual las etapas de vegetatividad y reproduccion solo ocurren una vez).
 * @author Luciano Peschiutta
 */
public class PlantaNoPerenne extends Planta{
    
    /**
     * Constructor de la clase, se inicia como viva, ademas los datos de humedad, temperatura y luminosidad inician en -1 hasta que sean actualizados.
     * @param nombre Nombre de la Planta 
     * @param fechaPlantado Fecha de plantado de la Planta
     */
    public PlantaNoPerenne(String nombre, String fechaPlantado){
        super(nombre, fechaPlantado);
        tipo = "NoPerenne";
    }
    
    /**
     * Metodo que incrementa el progreso de la etapa actual en 1
     * Al ser una planta no perenne la Planta muere al finalizar su etapa final
     * @return True si el progreso se incremento correctamente, False si no fue asi (la planta esta muerta)
     */
    @Override
    public boolean paso(){
        
        if(!etapas.isEmpty() && !flagMuerta){
            if(!etapas.get(0).paso()){

                etapas.remove(0);
                if(etapas.isEmpty()){
                    setMuerta(true);
                }  
            }
            return true;
        }else{
            return false;
        }
    }
}
