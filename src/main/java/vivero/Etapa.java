/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vivero;

enum Tipo{Germinacion, Crecimiento, Vegetativa, Reproduccion};

/**
 *
 * @author pesch
 */
public class Etapa {
    
    private Tipo tipo;
    
    protected float[] humedad = new float[2];
    protected float[] temperatura = new float[2];
    protected float[] luminosidad = new float[2];
    
    protected int duracion;
    protected int duracionActual;

    public Etapa(int tipo, float hMax, float hMin, float tMax, float tMin, float lMax, float lMin, int duracion) {
        
        switch(tipo){
            case 0 -> this.tipo = Tipo.Germinacion;
            case 1 -> this.tipo = Tipo.Crecimiento;
            case 2 -> this.tipo = Tipo.Vegetativa;
            case 3 -> this.tipo = Tipo.Reproduccion;
            default -> throw new IllegalArgumentException("Tipo Incorrecto");
        }
        
        humedad[0] = hMax;
        humedad[1] = hMin;
        
        temperatura[0] = tMax;
        temperatura[1] = tMin;

        luminosidad[0] = lMax;
        luminosidad[1] = lMin;
        
        this.duracion = duracion;
        duracionActual = 0;
        
    }
    
    
    public Etapa(Etapa etapa){
        
        switch(etapa.getOrdenTipo()){
            case 0 -> this.tipo = Tipo.Germinacion;
            case 1 -> this.tipo = Tipo.Crecimiento;
            case 2 -> this.tipo = Tipo.Vegetativa;
            case 3 -> this.tipo = Tipo.Reproduccion;
            default -> throw new IllegalArgumentException("Tipo Incorrecto");
        }
        
        humedad = etapa.getHumedad();
        temperatura = etapa.getTemperatura();
        luminosidad = etapa.getLuminosidad();
        duracion = etapa.getDuracion();
        duracionActual = 0;
    }
    
    public String getNombreTipo(){
        return tipo.name();
    }
    
    public int getOrdenTipo(){
        return tipo.ordinal();
    }

    public float[] getHumedad() {
        return humedad;
    }

    public float[] getTemperatura() {
        return temperatura;
    }

    public float[] getLuminosidad() {
        return luminosidad;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getDuracionActual() {
        return duracionActual;
    }
    
    public boolean paso(){
        duracionActual += 1;
        return duracionActual < duracion;
    }
    
}
