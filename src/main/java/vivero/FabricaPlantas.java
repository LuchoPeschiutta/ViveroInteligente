/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vivero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author pesch
 */
public class FabricaPlantas {
    
    Connection connect;
    
    public FabricaPlantas(){
        
        try{
            connect = DriverManager.getConnection("jdbc:sqlite:./src/main/java/vivero/CatalogoPlantas.s3db");
        }catch(SQLException e){
            System.out.println("No se pudo conectar a la DB" + e);
        }
        
    }
    
    public Planta crearPlanta(String nombre, int ubicacion, String fechaPlantado){

        Planta P;
        ResultSet result;
        
        try {
            PreparedStatement st = connect.prepareStatement("""
                                                            SELECT P.ID, P.Nombre, P.Tipo AS TipoPlanta, E.Tipo AS TipoEtapa, E.hMax, E.hMin, E.tMax, E.tMin, E.lMax, E.lMin, E.Duracion
                                                            FROM Plantas P JOIN PlantasEtapas PE ON P.ID=PE.PlantaID  JOIN Etapas E ON PE.EtapaID = E.ID
                                                            WHERE P.Nombre =\"""" + nombre + "\";");
            result = st.executeQuery();
            
            if(result.next()){
                switch (result.getInt("TipoPlanta")) {
                    case 1 -> P = new PlantaPerenne(result.getString("Nombre"), ubicacion, fechaPlantado);
                    case 2 -> P = new PlantaNoPerenne(result.getString("Nombre"), ubicacion, fechaPlantado);
                    default -> {
                        return null;
                    }
                }
            }else{
                return null;
            }
            
            do{
                
                P.agregarEtapa(new Etapa(result.getInt("TipoEtapa"), result.getFloat("hMax"), result.getFloat("hMin"), +
                                result.getFloat("tMax"), result.getFloat("tMin"), result.getFloat("lMax"), result.getFloat("lMin"), +
                                result.getInt("Duracion")));
                
            }while(result.next());
            
            return P;
            
        } catch (SQLException ex) {
            System.out.println("Error al realizar la query" + ex);
            return null;
        }
        
    }
    
    
    public Planta crearPlanta(String nombre, int ubicacion){
        
        Date date = new Date();
        
        return crearPlanta(nombre, ubicacion, date.toString());
        
    }
    
    public ArrayList<String> getListaTiposPlantas(){
        
        ArrayList<String> tiposPlantas = new ArrayList();
        ResultSet result;
        
        try {
            PreparedStatement st = connect.prepareStatement("""
                                                            SELECT Nombre From TiposPlantas;
                                                            """);
            result = st.executeQuery();
            
            while(result.next()){
                tiposPlantas.add(result.getString("Nombre"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al realizar la query" + ex);
        }
        
        return tiposPlantas;
    }
    
    public ArrayList<String> getListaPlantasPerennes(){
        
        ArrayList<String> plantasPerennes = new ArrayList();
        ResultSet result;
        
        try {
            PreparedStatement st = connect.prepareStatement("""
                                                            SELECT P.Nombre FROM Plantas P
                                                            JOIN TiposPlantas TP ON P.Tipo=TP.ID
                                                            WHERE TP.Nombre = "Perenne";""");
            result = st.executeQuery();
            
            while(result.next()){
                plantasPerennes.add(result.getString("Nombre"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al realizar la query" + ex);
        }
        
        return plantasPerennes;
    }
    
    public ArrayList<String> getListaPlantasNoPerennes(){
        
        ArrayList<String> plantasNoPerennes = new ArrayList();
        ResultSet result;
        
        try {
            PreparedStatement st = connect.prepareStatement("""
                                                            SELECT P.Nombre FROM Plantas P
                                                            JOIN TiposPlantas TP ON P.Tipo=TP.ID
                                                            WHERE TP.Nombre = "NoPerenne";""");
            result = st.executeQuery();
            
            while(result.next()){
                plantasNoPerennes.add(result.getString("Nombre"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al realizar la query" + ex);
        }
         
        return plantasNoPerennes;
    }
    
}
