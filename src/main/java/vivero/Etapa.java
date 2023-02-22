/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vivero;

/* Su utiliza un enum para indicar el tipo de etapa debido a que las etapas no difieren entre si
 * En el futuro, si cada etapa contiene funcionalidad especifica será necesario pasarse a la herencia
*/ 
enum Tipo{Germinacion, Crecimiento, Vegetativa, Reproduccion};

/**
 * Modela las etapas que atravieza una planta durante su ciclo de vida. Cada etapa puede pertenecer al tipo Germinacion, Crecimiento, Vegetativa o Reproducción.
 * Ademas contienen los valores limites de humedad, temperatura y luminosidad adecuados para la planta en dicha etapa.
 * Cuenta tambien con la duracion de la etapa y un contador de progreso. 
 * @author Luciano Peschiutta
 */
public class Etapa {
    
    private Tipo tipo;
    
    protected float[] humedad = new float[2];       //En la posicion 0 la maxima y en la 1 la minima
    protected float[] temperatura = new float[2];   //En la posicion 0 la maxima y en la 1 la minima
    protected float[] luminosidad = new float[2];   //En la posicion 0 la maxima y en la 1 la minima
    
    protected int duracion;
    protected int progreso;

    /**
     * Constructor de la clase, el progreso se inicia en 1
     * @param tipo Recibe un entero entre 0 y 3 indicando el tipo de etapa a construir (0:Germinacion; 1:Crecimiento; 2:Vegetativa; 3:Reproduccion)
     * @param hMax Indica la humedad maxima de la etapa
     * @param hMin Indica la humedad minima de la etapa
     * @param tMax Indica la inidicatemperatura maxima de la etapa
     * @param tMin Indica la temperatura minima de la etapa
     * @param lMax Indica la luminosidad maxima de la etapa
     * @param lMin Indica la luminosidad minima de la etapa
     * @param duracion Indica la duracion de la etapa 
     * @throws IllegalArgumentException Si tipo no es un entero entre 0 y 3 
     */
    public Etapa(int tipo, float hMax, float hMin, float tMax, float tMin, float lMax, float lMin, int duracion) {
        
        switch(tipo){
            case 0 -> this.tipo = Tipo.Germinacion;
            case 1 -> this.tipo = Tipo.Crecimiento;
            case 2 -> this.tipo = Tipo.Vegetativa;
            case 3 -> this.tipo = Tipo.Reproduccion;
            default -> throw new IllegalArgumentException("Tipo de etapa incorrecto");
        }
        
        humedad[0] = hMax;
        humedad[1] = hMin;
        
        temperatura[0] = tMax;
        temperatura[1] = tMin;

        luminosidad[0] = lMax;
        luminosidad[1] = lMin;
        
        this.duracion = duracion;
        progreso = 1;
        
    }
    
    /**
     * Constructor que genera una copia exacta de una etapa, con la excepcion de que su progreso se inicia nuevamente en 1
     * @param etapa Etapa a copiar
     */
    public Etapa(Etapa etapa){
        
        switch(etapa.getOrdenTipo()){
            case 0 -> this.tipo = Tipo.Germinacion;
            case 1 -> this.tipo = Tipo.Crecimiento;
            case 2 -> this.tipo = Tipo.Vegetativa;
            case 3 -> this.tipo = Tipo.Reproduccion;
            default -> throw new IllegalArgumentException("Tipo de etapa incorrecto");
        }
        
        humedad = etapa.getHumedad();
        temperatura = etapa.getTemperatura();
        luminosidad = etapa.getLuminosidad();
        duracion = etapa.getDuracion();
        progreso = 1;
    }
    
    /**
     * Geter del nombre del tipo de etapa
     * @return Nombre del tipo de etapa
     */
    public String getNombreTipo(){
        return tipo.name();
    }
    
    /**
     * Geter del numero de tipo de etapa
     * @return Valor entre 0 y 3 con el numero de tipo de etapa
     */
    public int getOrdenTipo(){
        return tipo.ordinal();
    }

    /**
     * Geter de rango de humedad
     * @return Dos elementos que contienen la humedad maxima([0]) y minima([1])
     */
    public float[] getHumedad() {
        return humedad;
    }

    /**
     * Geter del rango de temperatura
     * @return Dos elementos que contienen la temperatura maxima([0]) y minima([1])
     */
    public float[] getTemperatura() {
        return temperatura;
    }

    /**
     * Geter del rango de luminosidad
     * @return Dos elementos que contienen la luminosidad maxima([0]) y minima([1])
     */
    public float[] getLuminosidad() {
        return luminosidad;
    }

    /**
     * Geter de la duracion de la etapa
     * @return Duracion de la etapa
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Geter del progreso de la etapa
     * @return Progreso de la etapa
     */
    public int getProgreso() {
        return progreso;
    }
    
    /**
     * Incrementa el progreso de la etapa en 1 e indica si la etapa aun no termina
     * @return True si progreso <= duracion
     */
    public boolean paso(){
        progreso += 1;
        return progreso <= duracion;
    }
    
}
