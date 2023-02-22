/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package vivero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import org.json.*;


/**
 * Clase que representa un vivero que contiene plazas para que ocupen las plantas.
 * @author Luciano Peschiutta
 */
public class Vivero {
    
    protected HashMap<Integer, Planta> ubicacionesPlantas;
    protected FabricaPlantas fabrica;
    
    /**
     * Constructor de la clase
     */
    public Vivero(){
        
        ubicacionesPlantas = new HashMap<>();
        fabrica = new FabricaPlantas();
        
    }
    
    /**
     * Agrega una Planta a la ubicacion indicada
     * @param nombre Nombre de la planta a agregar, debe coincidir con el nombre de una planta del catalogo
     * @param ubicacion Ubicacion que ocupará la planta
     * @return True si la planta se creó y ubico correctamente, false en el caso contrario (la planta no se pudo crear por nombre incorrecto, la ubicacion ya estaba ocupada)
     */
    public boolean agregarPlanta(String nombre, int ubicacion){
        
        Planta planta;
        
        if(!ubicacionesPlantas.containsKey(ubicacion)){
            
            planta = fabrica.crearPlanta(nombre);
            
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
    
    /**
     * Elimina la Planta de la ubicacion seleccionada
     * @param ubicacion Ubicacion de la Planta a eliminar
     * @return True si se pudo eliminar, false en el caso contrario (la ubicacion no contenía Planta)
     */
    public boolean eliminarPlanta(int ubicacion){
        
        if(ubicacionesPlantas.containsKey(ubicacion)){
            
            ubicacionesPlantas.remove(ubicacion);
            return true;
            
        }else{
            return false;
        }
        
    }
    
    /**
     * Obtiene un array ordenado de las ubicaciones que contienen Plantas
     * @return Array ordenado de ubicaciones ocupadas
     */
    public Integer[] getUbicacionesOcupadas(){
        Set<Integer> set = ubicacionesPlantas.keySet();
        Integer[] lista = new Integer[set.size()];
        int i = 0;
        for(Integer valor : set){
            lista[i] = valor;
            i++;
        }
        Arrays.sort(lista);
        return lista;
    }
    
    /**
     * Comprueba si la ubicacion esta ocupada
     * @param ubicacion Ubicacion a comprobar
     * @return True si la ubicacion esta ocupada por una planta
     */
    public boolean ubicacionEstaOcupada(int ubicacion){
        return ubicacionesPlantas.keySet().contains(ubicacion);
    }

    /**
     * Actualiza el estado de la planta de la ubicacion indicada
     * @param ubicacion Ubicacion de la planta
     * @param humedad Nueva humedad
     * @param temperatura Nueva temperatura
     * @param luminosidad Nueva luminosidad
     * @return True si la actualizacion se llevo a cabo correctamente, false en el caso contrario (la ubicacion no contiene planta)
     */
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
    
    /**
     * Retorna el estado de la planta de la ubicacion seleccionada. Le añade al JSON la Ubicacion de la planta
     * @param ubicacion Ubicacion de la planta 
     * @return JSONObject con los datos de la planta + Ubicacion. Null en caso de error (la ubicacion no contiene planta)
     */
    public JSONObject getEstadoPlanta(int ubicacion){

        if(ubicacionesPlantas.containsKey(ubicacion)){
            
            JSONObject JS = ubicacionesPlantas.get(ubicacion).getEstado();
            JS.put("Ubicacion", ubicacion);
            return JS;
                    
        }else{
            return null;
        }
        
    }
    
    /**
     * Metodo encargado de adelantar el progreso todas las plantas del vivero
     * Llama al metodo paso() de todas las plantas del vivero
     */
    public void avanzarPaso(){
        
        for(Planta planta: ubicacionesPlantas.values()){
            planta.paso();
            /*
            if(!planta.paso()){
                System.out.println("La planta en ubicacion: " + Integer.toString(planta.getUbicacion()) + " a finalizado");
                //Llevar a cabo proceso de remocion, finalizacion, o lo que se disponga.
            }
            */
        }
        
    }
    
    /**
     * Obtiene la lista de tipos de plantas
     * @return Lista de tipos de plantas
     */
    public ArrayList<String> getListaTiposPlantas(){
        return fabrica.getListaTiposPlantas();
    }
    
     /**
      * Obtiene la lista de Plantas Perennes disponibles
      * @return Lista de Plantas Perennes
      */
    public ArrayList<String> getListaPlantasPerennes(){
        return fabrica.getListaPlantasPerennes();
    }
    
    /**
      * Obtiene la lista de Plantas NoPerennes disponibles
      * @return Lista de Plantas NoPerennes
      */
    public ArrayList<String> getListaPlantasNoPerennes(){
        return fabrica.getListaPlantasNoPerennes();
    }
    
}
