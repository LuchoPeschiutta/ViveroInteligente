/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vivero;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author pesch
 */
public class FabricaPlantas {
    
    protected ArrayList<String> plantasPerennes;
    protected ArrayList<String> plantasNoPerennes;
    
    public FabricaPlantas(){
        
        plantasPerennes = new ArrayList();
        plantasNoPerennes = new ArrayList();
        
        plantasPerennes.add("Pino");
        plantasPerennes.add("Rosa");
        plantasPerennes.add("Jazmin");
        plantasPerennes.add("Limon");
        
        plantasNoPerennes.add("Soja");
        plantasNoPerennes.add("Maiz");
        plantasNoPerennes.add("Alfa");
        plantasNoPerennes.add("Mani");
        
    }
    
    public Planta crearPlanta(String nombre, int ubicacion, String fechaPlantado){

        Planta P;
        
        // Se debe implementar acceso a DB que permita recuperar los datos especificos de cada planta

        switch (nombre){
            //Plantas No Perennes
            case "Soja", "Maiz", "Alfa", "Mani" -> P = new PlantaNoPerenne(nombre, ubicacion, fechaPlantado);
            //Plantas Perennes
            case "Pino", "Rosa", "Jazmin", "Limon" -> P = new PlantaPerenne(nombre, ubicacion, fechaPlantado);
            
            default -> {
                return null;
            }
        }
        
        P.agregarEtapa(new Etapa(0, 90, 50, 40, 15, 20, 30, 10));
        P.agregarEtapa(new Etapa(1, 90, 50, 40, 15, 20, 30, 10));
        P.agregarEtapa(new Etapa(2, 90, 50, 40, 15, 20, 30, 10));
        P.agregarEtapa(new Etapa(3, 90, 50, 40, 15, 20, 30, 10));
        
        
        return P;
        
    }
    
    
    public Planta crearPlanta(String nombre, int ubicacion){

        Planta P;
        
        Date date = new Date();
        
        // Se debe implementar acceso a DB que permita recuperar los datos especificos de cada planta

        switch (nombre){
            //Plantas No Perennes
            case "Soja", "Maiz", "Alfa", "Mani" -> P = new PlantaNoPerenne(nombre, ubicacion, date.toString());
            //Plantas Perennes
            case "Pino", "Rosa", "Jazmin", "Limon" -> P = new PlantaPerenne(nombre, ubicacion, date.toString());
            
            default -> {
                return null;
            }
        }
        
        P.agregarEtapa(new Etapa(0, 90, 50, 40, 15, 20, 30, 10));
        P.agregarEtapa(new Etapa(1, 90, 50, 40, 15, 20, 30, 10));
        P.agregarEtapa(new Etapa(2, 90, 50, 40, 15, 20, 30, 10));
        P.agregarEtapa(new Etapa(3, 90, 50, 40, 15, 20, 30, 10));
        
        
        return P;
        
    }
    
    public ArrayList<String> getPlantasPerennes(){
        return plantasPerennes;
    }
    
    public ArrayList<String> getPlantasNoPerennes(){
        return plantasNoPerennes;
    }
    
}
