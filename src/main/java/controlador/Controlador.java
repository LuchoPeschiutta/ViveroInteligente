package controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;
import java.util.Random;
import org.json.JSONObject;
import vivero.Vivero;
import vista.Vista;

/**
 *
 * @author pesch
 */
public class Controlador {
    

    protected Vivero vivero;
    protected Vista vista; 
    protected HiloSimulador simulador;
    
    
    public Controlador(){
        vivero = new Vivero();
        vista = new Vista(Controlador.this);
        simulador = new HiloSimulador(Controlador.this);
        simulador.start();
        
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
    
    public void simularParametros(){
        
        float humedad, temperatura, luminosidad;
        Random rnd = new Random();
        
        for(Integer i: vivero.getUbicacionesOcupadas()){
            
            //Humedad aleatoria entre 0 y 100
            humedad = rnd.nextFloat(100);
            //Temperatura aleatoria entre 5 y 45
            temperatura = rnd.nextFloat(40) + 5;
            //Luminosidad aleatoria entre 0 y 100
            luminosidad = rnd.nextFloat(100);
            vivero.actualizarPlanta(i, humedad, temperatura, luminosidad);
                    
        }
        
        vista.actualizarLista();
    }
    
    public void activarSimulador(){
        simulador.continuar();
    }
    
    public void desactivarSimulador(){
        simulador.detener();
    }
    
    
}
