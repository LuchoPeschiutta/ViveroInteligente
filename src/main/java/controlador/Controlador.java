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
        return vivero.getPlantasPerennes();
    }
    
    public ArrayList<String> getListaPlantasNoPerennes(){
        return vivero.getPlantasNoPerennes();
    }
    
    public Set<Integer> getUbicacionesOcupadas(){
        return vivero.getUbicacionesOcupadas();
    }
    
    public boolean crearPlanta(String nombre, int ubicacion){
        return vivero.agregarPlanta(nombre, ubicacion);
    }
    
    public ArrayList<JSONObject> getEstadosPlantas(){
        
        ArrayList<JSONObject> lista = new ArrayList();
        
        for(Integer i: vivero.getUbicacionesOcupadas()){
           lista.add(vivero.getEstadoPlanta(i));
        }
        
        return lista;
    }
    
    
}
