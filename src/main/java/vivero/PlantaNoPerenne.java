/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vivero;

/**
 *
 * @author pesch
 */
public class PlantaNoPerenne extends Planta{
    
    public PlantaNoPerenne(String nombre, int ubicacion, String fechaPlantado){
        super(nombre, ubicacion, fechaPlantado);
        tipo = "NoPerenne";
    }
    
    @Override
    public boolean paso(){
        
        if(!etapas.isEmpty()){
            if(!etapas.get(0).paso()){

                etapas.remove(0);
                if(etapas.isEmpty()){
                    matarPlanta();
                }  
            }
            return true;
        }else{
            return false;
        }
    }
}
