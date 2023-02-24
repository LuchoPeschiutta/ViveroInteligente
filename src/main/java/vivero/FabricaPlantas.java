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
            connect = DriverManager.getConnection("jdbc:sqlite:./src/main/java/vivero/CatalogoPlantas.db");
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
                                                            SELECT P.Nombre_Planta AS Nombre, P.Tipo_Planta_ID AS TipoPlanta, E.Tipo_Etapa AS TipoEtapa, E.hMax, E.hMin, E.tMax, E.tMin, E.lMax, E.lMin, E.Duracion 
                                                            FROM Plantas P JOIN Etapas E ON P.Planta_ID=E.Planta_ID
                                                            WHERE P.Nombre_Planta = \"""" + nombre + "\""+"""
                                                            ORDER BY E.Tipo_Etapa ASC
                                                            """);
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
                                                            SELECT Nombre_Tipo AS Nombre From TiposPlanta;
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
                                                            SELECT P.Nombre_Planta AS Nombre FROM Plantas P
                                                            JOIN TiposPlanta TP ON P.Tipo_Planta_ID=TP.Tipo_Planta_ID
                                                            WHERE TP.Nombre_Tipo = "Perenne";""");
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
                                                            SELECT P.Nombre_Planta AS Nombre FROM Plantas P
                                                            JOIN TiposPlanta TP ON P.Tipo_Planta_ID=TP.Tipo_Planta_ID
                                                            WHERE TP.Nombre_Tipo = "NoPerenne";""");
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

CREATE TABLE "TiposPlanta" (
	"Tipo_Planta_ID"    INTEGER NOT NULL,
	"Nombre_Tipo"       TEXT NOT NULL,
	PRIMARY KEY("Tipo_Planta_ID")
);

CREATE TABLE "Plantas" (
	"Planta_ID"         INTEGER NOT NULL,
	"Tipo_Planta_ID"    INTEGER NOT NULL,
	"Nombre_Planta"     TEXT NOT NULL,
	FOREIGN KEY("Tipo_Planta_ID") REFERENCES "TiposPlanta"("Tipo_Planta_ID"),
	PRIMARY KEY("Planta_ID" AUTOINCREMENT)
);

CREATE TABLE "Etapas" (
	"Etapa_ID"  INTEGER NOT NULL,
	"Tipo_Etapa"      INTEGER NOT NULL CHECK("Tipo_Etapa" >= 0 AND "Tipo_Etapa" <= 3),
	"hMax"      REAL NOT NULL,
	"hMin"      REAL NOT NULL,
	"tMax"      REAL NOT NULL,
	"tMin"      REAL NOT NULL,
	"lMax"      REAL NOT NULL,
	"lMin"      REAL NOT NULL,
	"Duracion"  INTEGER NOT NULL,
	"Planta_ID" INTEGER NOT NULL,
	PRIMARY KEY("Etapa_ID" AUTOINCREMENT),
	FOREIGN KEY("Planta_ID") REFERENCES "Plantas"("Planta_ID")
);

INSERT INTO TiposPlanta VALUES (1, "Perenne");
INSERT INTO TiposPlanta VALUES (2,"NoPerenne");

INSERT INTO Plantas VALUES(NULL, 1, "Pino");
INSERT INTO Plantas VALUES(NULL, 1, "Rosa");
INSERT INTO Plantas VALUES(NULL, 1, "Jazmin");
INSERT INTO Plantas VALUES(NULL, 1, "Limon");

INSERT INTO Plantas VALUES(NULL, 2, "Soja");
INSERT INTO Plantas VALUES(NULL, 2, "Maiz");
INSERT INTO Plantas VALUES(NULL, 2, "Alfa");
INSERT INTO Plantas VALUES(NULL, 2, "Mani");

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 100, 1);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 100, 1);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 100, 1);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 100, 1);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 110, 2);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 110, 2);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 110, 2);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 110, 2);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 120, 3);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 120, 3);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 120, 3);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 120, 3);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 130, 4);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 130, 4);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 130, 4);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 130, 4);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 10, 5);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 10, 5);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 10, 5);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 10, 5);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 15, 6);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 15, 6);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 15, 6);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 15, 6);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 20, 7);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 20, 7);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 20, 7);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 20, 7);

INSERT INTO Etapas VALUES (NULL, 0, 90.0, 10.0, 40.0, 10.5, 60, 10, 25, 8);
INSERT INTO Etapas VALUES (NULL, 1, 90.0, 15.0, 40.0, 10.5, 60, 10, 25, 8);
INSERT INTO Etapas VALUES (NULL, 2, 90.0, 20.0, 40.0, 10.5, 60, 10, 25, 8);
INSERT INTO Etapas VALUES (NULL, 3, 90.0, 25.0, 40.0, 10.5, 60, 10, 25, 8);



*/

