/**
 *
 * @author xcheko51x
 */
package com.mycompany.crudmongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Scanner;
import org.bson.Document;

public class CRUDMongoDB {
    public static void main(String[]args) {
        
        MongoClientURI uri = crearConexion();
        
        try(MongoClient mongoClient = new MongoClient(uri))
        {
            MongoDatabase database = mongoClient.getDatabase("Veterinaria");
            mostrarColeccion(database, "Perros");
            Scanner scan = new Scanner(System.in);
            
            //INSERTAR PIÑATA
            //System.out.println("Ingrese el nombre de la piñata");
            //String ingresarPiniata = scan.nextLine();
            //System.out.println("Ingrese el codigo de la piñata");
            //String ingresarCodigo = scan.nextLine();
            //insertarPiniata(database, "piniata", ingresarPiniata, ingresarCodigo);
            //mostrarColeccion(database, "piniata");

            
            //ACTUALIZAR PIÑATA
            //mostrarColeccion(database, "Perros");
            //System.out.println("Nombre del perro que se va a actualizar");
            //String actualizaPiniata = scan.nextLine();
            //System.out.println("Ingrese la raza del perro");
            //String actualizaCodigo= scan.nextLine();
            //actualizarPerro(database, "Perros", actualizaPiniata, actualizaCodigo);
            //mostrarColeccion(database, "Perros");

           
            //BUSCAR POR NOMBRE
            //System.out.println("Nombre de la piñata que está buscando");
            //mostrarColeccion(database, "piniata");
            //String buscarPiniata = scan.nextLine();
            //buscarPorNombre(database, "piniata", buscarPiniata);

           
            //ELIMINAR Perro
            //mostrarColeccion(database, "Perros");
            //System.out.println("Nombre del perro que se va a borrar");
            //String eliminaPiniata = scan.nextLine();
            //borrarPiniata(database, "Perros", eliminaPerro);
            //mostrarColeccion(database, "piniata");
            
        } catch (Exception e)
        {
        }
        
        
            
            
        
        
    }
    
    // METODO PARA CREAR LA CONEXION A MONGODB
    public static MongoClientURI crearConexion() {
        System.out.println("PRUEBA CONEXION MONGODB");
        
        MongoClientURI uri = null;
        
        uri = new MongoClientURI("mongodb+srv://Vane:Bacard1@veterinaria.av011d1.mongodb.net/?retryWrites=true&w=majority");
        
        return uri;
    }
    
    // MUESTRA TODOS LOS DOCUMENTOS DE LA COLECCION USUARIOS
    public static void mostrarColeccion(MongoDatabase database, String coleccion) {
        MongoCollection <Document> colect = database.getCollection(coleccion);
       
        MongoCursor <Document> document = colect.find().iterator();
        
        while(document.hasNext()) {
            ArrayList<Object> veri = new ArrayList(document.next().values());
            for (int i = 0; i < veri.size(); i++){
                System.out.println(veri.get(i)); 
           }
        }
    }
    
    public static void insertarPerro(MongoDatabase database, String coleccion, String nombre, String raza) {
        MongoCollection<Document> collection = database.getCollection(coleccion);
        
        // CREA EL DOCUMENTO(REGISTRO) E INSERTA LA INFORMACION RECIBIDA
        
        Document documento = new Document();
        documento.put("nombre", nombre);
        documento.put("raza", raza);
        collection.insertOne(documento);
        
    }
               
    // MUESTRA TODOS LOS DOCUMENTOS DE LA COLECCION USUARIOS QUE COINCIDAN CON EL NOMBRE
    public static void buscarPorNombre(MongoDatabase database, String coleccion, String nombre) {
        MongoCollection<Document> collection = database.getCollection(coleccion);
        
        // CREAMOS LA CONSULTA CON EL CAMPO NOMBRE
        Document consulta = new Document();
        consulta.put("nombre", nombre);
        
        // BUSCA Y MUESTRA TODOS LOS DOCUMENTOS QUE COINCIDAN CON LA CONSULTA
        MongoCursor<Document> cursor = collection.find(consulta).iterator();
        while(cursor.hasNext()) {
            System.out.println("-- " + cursor.next().get("raza"));
        }
    }
    
    // METODO PARA ACTUALIZAR UN DOCUMENTO (REGISTRO)
    public static void actualizarPerro(MongoDatabase database, String coleccion, String nombre, String raza) {
        MongoCollection<Document> collection = database.getCollection(coleccion);
        
        // SENTENCIA CON LA INFORMACION A REMPLAZAR
        Document actualizarPerro = new Document();
        actualizarPerro.append("$set", new Document().append("raza", raza));
        
        // BUSCA EL DOCUMENTO EN LA COLECCION
        Document buscarPorNombre = new Document();
        buscarPorNombre.append("nombre", nombre);
        
        // REALIZA EL UPDATE
        collection.updateOne(buscarPorNombre, actualizarPerro);
    }
    
    // METODO PARA ELIMINAR UN DOCUMENTO (REGISTRO)
    public static void borrarPerro(MongoDatabase database, String coleccion, String nombre) {
        MongoCollection<Document> collection = database.getCollection(coleccion);
        
        collection.deleteOne(new Document().append("nombre", nombre));
    }
}
