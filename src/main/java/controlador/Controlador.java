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
    
    public void actualizarTablaPlantas(){
        ArrayList<JSONObject> listaEstados = getEstadosPlantas();
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                vista.actualizarTablaPlantas(listaEstados);
            }
        });
    }
    
    public void actualizarListaPlantas(int tipo){
        
        ArrayList<String> listaPlantas;
        
        if(tipo == 0){
            listaPlantas = getListaPlantasPerennes();
        }else{
            listaPlantas = getListaPlantasNoPerennes();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                vista.actualizarListaPlantas(listaPlantas);
            }
        });
    }
    
    public void MessageDialog(String mensaje){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                vista.generarMessageDialog(mensaje);
            }
        });
    }
    
    public void crearPlanta(String nombre, int ubicacion){
        
        if(ubicacionEstaOcupada(ubicacion)){
            MessageDialog("La ubicacion seleccionada ya esta ocupada");
        }else{
            if(vivero.agregarPlanta(nombre, ubicacion)){
                actualizarTablaPlantas();
            }else{
                MessageDialog("Error inesperado al crear la nueva planta");
            }
        }
    }
    
    public void eliminarPlanta(int ubicacion){
        
        if(!ubicacionEstaOcupada(ubicacion)){
            MessageDialog("Esta ubicacion no contiene nunguna planta");
        }else{
            if(vivero.eliminarPlanta(ubicacion)){
                actualizarTablaPlantas();
            }else{
                MessageDialog("Error inesperado al remover la planta");
            }
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
        actualizarTablaPlantas();
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
        
        actualizarTablaPlantas();
    }
    
    public void activarSimulador(){
        if(simulador == null){
            simulador = new HiloSimulador(Controlador.this);
            simulador.start();
        }
        
    }
    
    public void desactivarSimulador(){
        simulador.detener();
        simulador = null;
    }
    
    
}
