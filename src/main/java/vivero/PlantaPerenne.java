/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vivero;

import org.json.JSONObject;

/**
 *
 * @author pesch
 */
public class PlantaPerenne extends Planta{
    
    protected int cantidadReproducciones;

    public PlantaPerenne(String nombre, int ubicacion, String fechaPlantado) {
        super(nombre, ubicacion, fechaPlantado);
        tipo = "Perenne";
        cantidadReproducciones = 0;
    }
    
    @Override
    public boolean paso(){
        
        if(!etapas.isEmpty()){
        
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
    
    @Override
    public JSONObject getEstado(){
        
        JSONObject JS = super.getEstado();
        JS.put("CantidadReproducciones", cantidadReproducciones);
        return JS;
        
    }
    
}
