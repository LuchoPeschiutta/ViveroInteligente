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
 * Clase encargada de generar nuevas plantas.
 * @author Luciano Peschiutta
 */
public class FabricaPlantas {
    
    Connection connect;
    
    /**
     * Constructor de la clase
     * Abre una conexion con la DB Catalogo de Plantas
     */
    public FabricaPlantas(){
        
        try{
            connect = DriverManager.getConnection("jdbc:sqlite:./src/main/java/vivero/CatalogoPlantas.s3db");
        }catch(SQLException e){
            System.out.println("No se pudo conectar a la DB" + e);
        }
        
    }
    
    /**
     * Crea una planta nueva extrayendo sus datos de la DB
     * @param nombre Nombre de la planta, debe coincidir con el nombre de alguna de las plantas de la DB
     * @param fechaPlantado Fecha de plantado
     * @return Planta creada o null en caso de error
     */
    public Planta crearPlanta(String nombre, String fechaPlantado){

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
                    case 1 -> P = new PlantaPerenne(result.getString("Nombre"), fechaPlantado);
                    case 2 -> P = new PlantaNoPerenne(result.getString("Nombre"), fechaPlantado);
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
    
    /**
     * Sobrecarga del metodo crearPlanta donde se toma el momento actual como fecha de plantado
     * @param nombre Nombre de la planta, debe coincidir con el nombre de alguna de las plantas de la DB
     * @return Planta creada o null en caso de error
     */
    public Planta crearPlanta(String nombre){
        
        Date date = new Date();
        
        return crearPlanta(nombre, date.toString());
        
    }
    
    /**
     * Obtiene una lista con los diferentes tipos de plantas existentes en la DB
     * @return Lista de nombres de tipos de plantas
     */
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
    
    /**
     * Obtiene una lista con los nombres de Plantas Perennes de la DB 
     * @return Lista de nombres de Plantas Perennes
     */
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
    
    /**
     * Obtiene una lista con los nombres de Plantas NoPerennes de la DB 
     * @return Lista de nombres de Plantas NoPerennes
     */
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

/* Creado y llenado de la DB CatalogoPlantas de muestra:

CREATE TABLE [Plantas] (
[ID] INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL,
[Tipo] INTEGER  NOT NULL,
[Nombre] VARCHAR(50)  NOT NULL
);

CREATE TABLE [TiposPlantas] (
[ID] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,
[Nombre] VARCHAR(30)  NOT NULL
);

CREATE TABLE [Etapas] (
[ID] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,
[Tipo] INTEGER  NOT NULL,
[hMax] FLOAT  NOT NULL,
[hMin] FLOAT  NOT NULL,
[tMax] FLOAT  NOT NULL,
[tMin] FLOAT  NOT NULL,
[lMax] FLOAT  NOT NULL,
[lMin] FLOAT  NOT NULL,
[Duracion] INTEGER  NOT NULL
);

CREATE TABLE [PlantasEtapas] (
[PlantaID] INTEGER  NOT NULL,
[EtapaID] INTEGER  NOT NULL,
PRIMARY KEY ([PlantaID],[EtapaID])
);

INSERT INTO Tipos VALUES (NULL, "Perenne");
INSERT INTO Tipos VALUES (NULL,"NoPerenne");

INSERT INTO Plantas VALUES(NULL, 1, "Pino");
INSERT INTO Plantas VALUES(NULL, 1, "Rosa");
INSERT INTO Plantas VALUES(NULL, 1, "Jazmin");
INSERT INTO Plantas VALUES(NULL, 1, "Limon");

INSERT INTO Plantas VALUES(NULL, 2, "Soja");
INSERT INTO Plantas VALUES(NULL, 2, "Maiz");
INSERT INTO Plantas VALUES(NULL, 2, "Alfa");
INSERT INTO Plantas VALUES(NULL, 2, "Mani");


INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 100);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 100);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 100);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 100);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 110);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 110);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 110);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 110);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 120);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 120);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 120);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 120);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 130);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 130);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 130);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 130);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 10);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 10);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 10);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 10);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 15);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 15);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 15);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 15);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 20);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 20);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 20);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 20);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 25);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 25);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 25);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 25);


INSERT INTO PlantasEtapas VALUES (1, 1);
INSERT INTO PlantasEtapas VALUES (1, 2);
INSERT INTO PlantasEtapas VALUES (1, 3);
INSERT INTO PlantasEtapas VALUES (1, 4);

INSERT INTO PlantasEtapas VALUES (2, 5);
INSERT INTO PlantasEtapas VALUES (2, 6);
INSERT INTO PlantasEtapas VALUES (2, 7);
INSERT INTO PlantasEtapas VALUES (2, 8);

INSERT INTO PlantasEtapas VALUES (3, 9);
INSERT INTO PlantasEtapas VALUES (3, 10);
INSERT INTO PlantasEtapas VALUES (3, 11);
INSERT INTO PlantasEtapas VALUES (3, 12);

INSERT INTO PlantasEtapas VALUES (4, 13);
INSERT INTO PlantasEtapas VALUES (4, 14);
INSERT INTO PlantasEtapas VALUES (4, 15);
INSERT INTO PlantasEtapas VALUES (4, 16);

INSERT INTO PlantasEtapas VALUES (5, 17);
INSERT INTO PlantasEtapas VALUES (5, 18);
INSERT INTO PlantasEtapas VALUES (5, 19);
INSERT INTO PlantasEtapas VALUES (5, 20);

INSERT INTO PlantasEtapas VALUES (6, 21);
INSERT INTO PlantasEtapas VALUES (6, 22);
INSERT INTO PlantasEtapas VALUES (6, 23);
INSERT INTO PlantasEtapas VALUES (6, 24);

INSERT INTO PlantasEtapas VALUES (7, 25);
INSERT INTO PlantasEtapas VALUES (7, 26);
INSERT INTO PlantasEtapas VALUES (7, 27);
INSERT INTO PlantasEtapas VALUES (7, 28);

INSERT INTO PlantasEtapas VALUES (8, 29);
INSERT INTO PlantasEtapas VALUES (8, 30);
INSERT INTO PlantasEtapas VALUES (8, 31);
INSERT INTO PlantasEtapas VALUES (8, 32);

*/

