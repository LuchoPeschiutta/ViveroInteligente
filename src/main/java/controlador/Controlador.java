package controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;
import java.util.Set;
import org.json.JSONObject;
import vivero.Vivero;
import vista.Vista;

/**
 *
 * @author pesch
 */
public class Controlador {
    

    Vivero vivero;
    Vista vista; 
    
    
    public Controlador(){
        vivero = new Vivero();
        vista = new Vista(Controlador.this);
        
        vista.setVisible(true);
    }
    
    public ArrayList<String> getListaPlantasPerennes(){
        return vivero.getListaPlantasPerennes();
    }
    
    public ArrayList<String> getListaPlantasNoPerennes(){
        return vivero.getListaPlantasNoPerennes();
    }
    
    public ArrayList<String> getListaTiposPlantas(){
        return vivero.getListaTiposPlantas();
    }
    
    public Integer[] getUbicacionesOcupadas(){
        return vivero.getUbicacionesOcupadas();
    }
    
    public boolean ubicacionEstaOcupada(int ubicacion){
        return vivero.ubicacionEstaOcupada(ubicacion);
    }
    
    public boolean crearPlanta(String nombre, int ubicacion){
        if(vivero.agregarPlanta(nombre, ubicacion)){
            vista.actualizarLista();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean eliminarPlanta(int ubicacion){
        if(vivero.eliminarPlanta(ubicacion)){
            vista.actualizarLista();
            return true;
        }else{
            return false;
        }
    }
    
    public ArrayList<JSONObject> getEstadosPlantas(){
        
        ArrayList<JSONObject> lista = new ArrayList();
        
        for(Integer i: vivero.getUbicacionesOcupadas()){
           lista.add(vivero.getEstadoPlanta(i));
        }
        
        return lista;
    }
    
    public void avanzarPaso(){
        vivero.avanzarPaso();
        vista.actualizarLista();
    }
    
    
}
