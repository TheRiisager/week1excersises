/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author riisager
 */
@Path("employee")
public class EmployeeResource {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
    }
    
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployees(){
        
        List<EmployeeDTO> dtoList = new ArrayList<EmployeeDTO>();
        
        for ( Employee e : facade.getAllEmployees() ) {
            dtoList.add( new EmployeeDTO( e ) );
        }
        
        return gson.toJson( dtoList );
    }
    
    @Path("/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeByID( @PathParam("id") long id ) {
        
        EmployeeDTO result = new EmployeeDTO( facade.getEmployeeByID( id ) );
        
        return gson.toJson( result );
    }
    
    @Path("/highestPaid")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighestPaidEmployee() {
        
        EmployeeDTO result = new EmployeeDTO( facade.getEmployeeWithHighestSalary() );
        
        return gson.toJson( result );
    }
    
    @Path("/name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeByName( @PathParam("name") String name ) {
        
        EmployeeDTO result = new EmployeeDTO( facade.getEmployeeByName( name ) );
        
        return gson.toJson( result );
        
    }
    
}
