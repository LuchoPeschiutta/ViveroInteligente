package controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;
import java.util.Random;
import javax.swing.SwingUtilities;
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
    
    public static void main(String[] args) { 
         
        Controlador controlador = new Controlador();
        
    }
    
    
    public Controlador(){
        vivero = new Vivero();
        
        simulador = new HiloSimulador(Controlador.this);
        simulador.start();
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                vista = new Vista(Controlador.this);
                vista.setVisible(true);
            }
        });

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
    
    public void actualizarVista(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                vista.actualizarLista();
            }
        });
    }
    
    public boolean crearPlanta(String nombre, int ubicacion){
        if(vivero.agregarPlanta(nombre, ubicacion)){
            actualizarVista();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean eliminarPlanta(int ubicacion){
        if(vivero.eliminarPlanta(ubicacion)){
            actualizarVista();
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
        actualizarVista();
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
        
        actualizarVista();
    }
    
    public void activarSimulador(){
        simulador.continuar();
    }
    
    public void desactivarSimulador(){
        simulador.detener();
    }
    
    
}
