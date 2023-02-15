/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vivero;

import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author pesch
 */
public abstract class Planta {
    
    protected ArrayList<Etapa> etapas;
    protected String nombre;
    protected String tipo;
    protected int ubicacion;
    protected float humedad;
    protected float temperatura;
    protected float luminosidad;
    protected String fechaPlantado;
    protected boolean flagMuerta;
    
    public Planta(String nombre, int ubicacion, String fechaPlantado){
        etapas = new ArrayList<>();
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fechaPlantado = fechaPlantado;
        temperatura = -1;
        humedad = -1;
        luminosidad = -1;
        flagMuerta = false;
    }

    public ArrayList<Etapa> getEtapas() {
        return etapas;
    }
    
    public Etapa getEtapaActual(){
        if(!etapas.isEmpty()){
            return etapas.get(0);
        }else{
            return null;
        }
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getTipo(){
        return tipo;
    }

    public int getUbicacion() {
        return ubicacion;
    }
    
        public float getHumedad() {
        return humedad;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public float getLuminosidad() {
        return luminosidad;
    }

    public String getFechaPlantado() {
        return fechaPlantado;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }
    
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public void setLuminosidad(float luminosidad) {
        this.luminosidad = luminosidad;
    }
    
    public void agregarEtapa(Etapa etapa){
        etapas.add(etapa);
    }
    
    protected void quitarEtapa(int index){
        etapas.remove(index);
    }
    
    public boolean estaMuerta(){
        return flagMuerta;
    }
    
    public void matarPlanta(){
        flagMuerta = true;
    }
    
    public JSONObject getEstado(){
        JSONObject JS = new JSONObject();
        Etapa etapa = getEtapaActual();
        
        JS.put("Nombre", getNombre());
        JS.put("Tipo", getTipo());
        JS.put("Ubicacion", getUbicacion());
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
            JS.put("DuracionLimite", etapa.getDuracionLimite());
            JS.put("DuracionActual", etapa.getDuracionActual());
        }

        return JS;
    }
    
    public abstract boolean paso();
    
    
}
