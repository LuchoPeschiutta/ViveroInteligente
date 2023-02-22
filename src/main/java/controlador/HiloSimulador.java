/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 * Subclase de Thread que se encarga de actualizar el progreso y simular valores constantemente para el Controlador.
 * @author Luciano Peschiutta
 */
public class HiloSimulador extends Thread{
    
    Controlador controlador;
    boolean simulando;
    int contador;
    
    /**
     * Constructor de la Clase
     * @param controlador Referencia al Controlador con el que debe interactuar
     */
    public HiloSimulador(Controlador controlador){
        
        this.controlador = controlador;
        simulando = false;
        contador = 1;
        
    }
    
    /**
     * Llama a los metodos avanzarPaso() y simularParametros() del controlador mientras la variable simulando == true
     */
    @Override
    public void run(){
        simulando = true;
        while(simulando){
            //System.out.println("Simulando...");
            controlador.simularParametros();
            if(contador%3 == 0){
                controlador.avanzarPaso();
            }
            contador++;

            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                System.out.println("Sleep de HiloSimulador interrumpido");
            }
        }
        //System.out.println("Simulacion detenida");
    }
    
    /**
     * Actualiza el valor de la variable simulando a false, lo que provoca la finalizacion del hilo
     */
    public synchronized void detener(){
        simulando = false;
    }
}
