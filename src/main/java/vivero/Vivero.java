/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package vivero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.json.*;


/**
 *
 * @author pesch
 */
public class Vivero {
    
    protected HashMap<Integer, Planta> ubicacionesPlantas;
    protected FabricaPlantas fabrica;
    
    
    public Vivero(){
        
        ubicacionesPlantas = new HashMap<>();
        fabrica = new FabricaPlantas();
        
    }
    
    public boolean agregarPlanta(String nombre, int ubicacion){
        
        Planta planta;
        
        if(!ubicacionesPlantas.containsKey(ubicacion)){
            
            planta = fabrica.crearPlanta(nombre, ubicacion);
            
            if(planta != null){
                ubicacionesPlantas.put(ubicacion, planta);
                return true;
            }else{
                return false;
            } 
        }else{
            return false;
        }
        
    }
    
    public boolean eliminarPlanta(int ubicacion){
        
        if(ubicacionesPlantas.containsKey(ubicacion)){
            
            ubicacionesPlantas.remove(ubicacion);
            return true;
            
        }else{
            return false;
        }
        
    }
    
    public Integer[] getUbicacionesOcupadas(){
        Set<Integer> set = ubicacionesPlantas.keySet();
        Integer[] lista = new Integer[set.size()];
        int i = 0;
        for(Integer valor : set){
            lista[i] = valor;
            i++;
        }
        return lista;
    }
    
    public boolean ubicacionEstaOcupada(int ubicacion){
        return ubicacionesPlantas.keySet().contains(ubicacion);
    }

    public boolean actualizarPlanta(int ubicacion, float humedad, float temperatura, float luminosidad){
       
       Planta planta;
       
       if(ubicacionesPlantas.containsKey(ubicacion)){
           planta = ubicacionesPlantas.get(ubicacion);
           planta.setHumedad(humedad);
           planta.setTemperatura(temperatura);
           planta.setLuminosidad(luminosidad);
           return true;
       }else{
           return false;
       }
    }
    
    
    public JSONObject getEstadoPlanta(int ubicacion){

        if(ubicacionesPlantas.containsKey(ubicacion)){
            
            return ubicacionesPlantas.get(ubicacion).getEstado();
                    
        }else{
            return null;
        }
        
    }
    
    
    public void avanzarPaso(){
        
        for(Planta planta: ubicacionesPlantas.values()){
            if(!planta.paso()){
                System.out.println("La planta en ubicacion: " + Integer.toString(planta.getUbicacion()) + " a finalizado");
                //Llevar a cabo proceso de remocion o lo que se disponga.
            }
        }
        
    }
    
    
    public ArrayList<String> getListaPlantasPerennes(){
        return fabrica.getListaPlantasPerennes();
    }
    
    public ArrayList<String> getListaPlantasNoPerennes(){
        return fabrica.getListaPlantasNoPerennes();
    }
    
}
