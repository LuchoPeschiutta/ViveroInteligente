/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author pesch
 */
public class HiloSimulador extends Thread{
    
    Controlador controlador;
    boolean simulando;
    int contador;
    
    public HiloSimulador(Controlador controlador){
        
        this.controlador = controlador;
        simulando = false;
        contador = 0;
        
    }
    
    @Override
    public void run(){
        
        while(true){
            
            while(simulando){

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


            synchronized(this){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    System.out.println("Error en wait de HiloSimulador");
                }
            }
        }
    }
    
    public synchronized void continuar(){
        simulando = true;
        notify();
    }
    
    public synchronized void detener(){
        simulando = false;
    }
}
