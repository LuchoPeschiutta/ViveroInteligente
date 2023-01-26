/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import vivero.Vivero;

/**
 *
 * @author pesch
 */
public class Main {
    
     public static void main(String[] args) {
         
         
         Vivero vivero = new Vivero();
         
         vivero.agregarPlanta("Pino", 0);
         vivero.agregarPlanta("Maiz",1);
         
         System.out.println(vivero.getEstadoPlanta(0));
         
         for(int i=0; i<42; i++){
             vivero.avanzarPaso();
         }
  
        
    }
    
}
