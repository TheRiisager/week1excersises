/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author riisager
 */
@Path("animals_db")
public class AnimalFromDB {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return gson.toJson(animals);
         } finally {
                em.close();
         }
    }
    
    @Path("animalsByID/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimal(@PathParam("id") int id){
        EntityManager em = emf.createEntityManager();
       
        try{
            
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.id = :id", Animal.class);
            
            Animal result = query.setParameter("id",id).getSingleResult();
            return gson.toJson(result);
            
        }finally{
            em.close();
        }
    }
    
    @Path("animalsByType/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type){
        EntityManager em = emf.createEntityManager();
       
        try{
            
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.type = :type", Animal.class);
            
            Animal result = query.setParameter("type",type).getSingleResult();
            
            if( result.equals(null) ){
                return null;
            } else {
                return gson.toJson(result);
            }
            
        }finally{
            em.close();
        }
    }
    
    @Path("random_animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomAnimal(){
        EntityManager em = emf.createEntityManager();
        Random r = new Random();
       
        try{
            
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            
            List<Animal> aList = query.getResultList();
            
            int selector = r.nextInt( aList.size() );
            
            
            
            return gson.toJson( aList.get( selector ) );
            
        }finally{
            em.close();
        }
    }
     
}
