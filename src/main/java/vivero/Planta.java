/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vivero;

import java.util.ArrayList;
import org.json.JSONObject;

/**
 * Clase abstracta de Planta, contine una lista de etapas, nombre, tipo, valores actuales de humedad, temperatura y luminosidad.
 * Incluye tambien fecha de plantado y un indicador de si la Planta esta viva o muerta.
 * @author Luciano Peschiutta
 */
public abstract class Planta {
    
    protected ArrayList<Etapa> etapas;
    protected String nombre;
    protected String tipo;
    protected float humedad;
    protected float temperatura;
    protected float luminosidad;
    protected String fechaPlantado;
    protected boolean flagMuerta;
    
    /**
     * Constructor de la clase, se inicia como viva, ademas los datos de humedad, temperatura y luminosidad inician en -1 hasta que sean actualizados
     * @param nombre Nombre de la Planta 
     * @param fechaPlantado Fecha de plantado de la Planta
     */
    public Planta(String nombre, String fechaPlantado){
        etapas = new ArrayList<>();
        this.nombre = nombre;
        tipo = "";
        this.fechaPlantado = fechaPlantado;
        temperatura = -1;
        humedad = -1;
        luminosidad = -1;
        flagMuerta = false;
    }
    
    /**
     * Geter de la lista de etapas
     * @return Lista de etapas de la planta
     */
    public ArrayList<Etapa> getEtapas() {
        return etapas;
    }
    
    /**
     * Geter de la etapa actual por la que transcurre la Planta (la primera de la lista)
     * @return Etapa actual de la planta si la tiene, null en el otro caso
     */
    public Etapa getEtapaActual(){
        if(!etapas.isEmpty()){
            return etapas.get(0);
        }else{
            return null;
        }
    }

    /**
     * Geter del nombre
     * @return Nombre de la planta
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Geter del tipo de planta
     * @return Nombre del tipo de planta
     */
    public String getTipo(){
        return tipo;
    }
    
    /**
     * Geter de humedad
     * @return Humedad
     */
    public float getHumedad() {
        return humedad;
    }

    /**
     * Geter de temperatura
     * @return Temperatura
     */
    public float getTemperatura() {
        return temperatura;
    }

    /**
     * Geter de luminosidad
     * @return Luminosidad
     */
    public float getLuminosidad() {
        return luminosidad;
    }

    /**
     * Geter de fecha de plantado
     * @return Fecha de plantado
     */
    public String getFechaPlantado() {
        return fechaPlantado;
    }

    /**
     * Seter de humedad
     * @param humedad Humedad
     */
    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }
    
    /**
     * Seter de temperatura 
     * @param temperatura Temperatura
     */
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * Seter de luminosidad
     * @param luminosidad Luminosidad
     */
    public void setLuminosidad(float luminosidad) {
        this.luminosidad = luminosidad;
    }
    
    /**
     * Agrega la etapa al final de la lista de etapas
     * @param etapa Etapa a agregar
     */
    public void agregarEtapa(Etapa etapa){
        etapas.add(etapa);
    }
    
    /**
     * Quita una etapa de la lista de etapas
     * @param index Index de la etapa en la lista
     * @throws IndexOutOfBoundsException Si (index < 0 || index >0 size()) 
     */
    protected void quitarEtapa(int index){
        etapas.remove(index);
    }
    
    /**
     * Indica si la planta esta muerta
     * @return True si la planta esta muerta
     */
    public boolean estaMuerta(){
        return flagMuerta;
    }
    
    /**
     * Seter del estado de vida de la plata
     * @param muerta Estado de vida de la planta
     */
    public void setMuerta(boolean muerta){
        flagMuerta = muerta;
    }
    
    /**
     * Retorna los datos de la planta
     * @return JSONObject con los datos y estado de la planta
     * El JSON contiene: Nombre, Tipo, Humedad, Temperatura, Luminosidad, FechaPlantado, Muerta.
     * Si la planta contiene etapas, entonces incluira los siguientes datos de la etapa actual: Etapa, hMax, hMin, tMax, tMin, lMax, lMin, Duracion, Progreso.
     */
    public JSONObject getEstado(){
        JSONObject JS = new JSONObject();
        Etapa etapa = getEtapaActual();
        
        JS.put("Nombre", getNombre());
        JS.put("Tipo", getTipo());
        JS.put("Humedad", getHumedad());
        JS.put("Temperatura", getTemperatura());
        JS.put("Luminosidad", getLuminosidad());
        JS.put("FechaPlantado", getFechaPlantado());
        JS.put("Muerta", estaMuerta());
        
        if(etapa != null ){
            JS.put("Etapa", etapa.getNombreTipo());
            JS.put("hMax", etapa.getHumedad()[0]);
            JS.put("hMin", etapa.getHumedad()[1]);
            JS.put("tMax", etapa.getTemperatura()[0]);
            JS.put("tMin", etapa.getTemperatura()[1]);
            JS.put("lMax", etapa.getLuminosidad()[0]);
            JS.put("lMin", etapa.getLuminosidad()[1]);
            JS.put("Duracion", etapa.getDuracion());
            JS.put("Progreso", etapa.getProgreso());
        }

        return JS;
    }
    
    /**
     * Metodo abstracto que debe implementar la logica de avanze de las etapas
     * @return 
     */
    public abstract boolean paso();
    
    
}
