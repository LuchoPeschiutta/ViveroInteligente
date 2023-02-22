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
 * Clase que toma el rol de Controlador del modelo MVC
 * @author Luciano Peschiutta
 */
public class Controlador {
    

    protected Vivero vivero;
    protected Vista vista; 
    protected HiloSimulador simulador;
    
    /**
     * Main de la aplicacion. 
     * Crea un controlador y finaliza.
     * @param args 
     */
    public static void main(String[] args) { 
         
        Controlador controlador = new Controlador();
        
    }
    
    /**
     * Constructor de la clase
     * Crea la Vista
     */
    public Controlador(){
        vivero = new Vivero();
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                vista = new Vista(Controlador.this);
                vista.setVisible(true);
            }
        });

    }
    
    /**
      * Obtiene la lista de Plantas Perennes disponibles
      * @return Lista de Plantas Perennes
      */
    public ArrayList<String> getListaPlantasPerennes(){
        return vivero.getListaPlantasPerennes();
    }
    
    /**
      * Obtiene la lista de Plantas NoPerennes disponibles
      * @return Lista de Plantas NoPerennes
      */
    public ArrayList<String> getListaPlantasNoPerennes(){
        return vivero.getListaPlantasNoPerennes();
    }
    
    /**
     * Obtiene la lista de tipos de plantas
     * @return Lista de tipos de plantas
     */
    public ArrayList<String> getListaTiposPlantas(){
        return vivero.getListaTiposPlantas();
    }
    
    /**
     * Obtiene un array ordenado de las ubicaciones que contienen Plantas del Vivero
     * @return Array ordenado de ubicaciones ocupadas
     */
    public Integer[] getUbicacionesOcupadas(){
        return vivero.getUbicacionesOcupadas();
    }
    
    /**
     * Comprueba si la ubicacion del Vivero esta ocupada
     * @param ubicacion Ubicacion a comprobar
     * @return True si la ubicacion esta ocupada por una planta
     */
    public boolean ubicacionEstaOcupada(int ubicacion){
        return vivero.ubicacionEstaOcupada(ubicacion);
    }
    
    /**
     * Genera una task para que el EDT de la Vista actualize la tabla de Plantas
     * Pasa la lista de estados de plantas obtenidos del Vivero
     */
    public void actualizarTablaPlantas(){
        ArrayList<JSONObject> listaEstados = getEstadosPlantas();
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                vista.actualizarTablaPlantas(listaEstados);
            }
        });
    }
    
    /**
     * Genera una task para que el EDT de la Vista actualize la lista de Plantas
     * @param tipo 0 para listar PlantasPerennes y 1 para PlantasNoPerennes
     */
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
    
    /**
     * Genera una task para que el EDT de la Vista mueste un MessageDialog
     * @param mensaje Mensaje que contendra el MessageDialog
     */
    public void MessageDialog(String mensaje){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                vista.generarMessageDialog(mensaje);
            }
        });
    }
    
    /**
     * Crea una planta en el vivero
     * Interactua con la Vista mediante tasks para su EDT, que indican si se produjeron errores
     * @param nombre Nombre de la nueva Planta
     * @param ubicacion Ubicacion de la nueva Planta
     */
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
    
    /**
     * Elimina la planta de la ubicacion especificada
     * Interactua con la Vista mediante tasks para su EDT, que indican si se produjeron errores
     * @param ubicacion Ubicacion de la que se desea remover la planta
     */
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
    
    /**
     * Retorna una lista con los estados de todas las plantas del vivero
     * @return Lista con estados de plantas 
     */
    public ArrayList<JSONObject> getEstadosPlantas(){
        
        ArrayList<JSONObject> lista = new ArrayList();
        
        for(Integer i: vivero.getUbicacionesOcupadas()){
           lista.add(vivero.getEstadoPlanta(i));
        }
        
        return lista;
    }
    
    /**
     * Llama al metodo avanzarPaso() del Vivero y actualiza la Vista mediante actualizarTablaPlantas()
     */
    public void avanzarPaso(){
        vivero.avanzarPaso();
        actualizarTablaPlantas();
    }
    
    /**
     * Cambia los parametros actuales de todas las Plantas del Vivero, obteniendolos de manera aleatoria
     * Actualiza la Vista a traves de actualizarTablaPlantas()
     */
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
    
    /**
     * Crea un nuevo Thread de HiloSimulador para que actualize el progreso de las plantas del vivero y sus parametros constantemente
     */
    public void activarSimulador(){
        if(simulador == null){
            simulador = new HiloSimulador(Controlador.this);
            simulador.start();
        }  
    }
    
    /**
     * Detiene el Thread HiloSimulador
     */
    public void desactivarSimulador(){
        if(simulador == null){
            simulador.detener();
            simulador = null;
        }
    }
    
    
}
