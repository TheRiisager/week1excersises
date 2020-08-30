/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManagerFactory;
import entities.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author riisager
 */
public class EmployeeFacade {
    
    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
    
    private EmployeeFacade() {}
    
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if( instance == null ){
            emf = _emf;
            instance = new EmployeeFacade();
        }
        
        return instance;
    }
    
    public Employee createEmployee(String name, String address, BigDecimal salary) {
        EntityManager em = emf.createEntityManager();
        Employee employee = new Employee(name, address, salary);
        
        try{
            
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            
            return employee;
            
        }finally{
            em.close();
        }
    }
    
    public Employee getEmployeeByID(long id) {
        EntityManager em = emf.createEntityManager();
        
        try{
            
            Employee result = em.find( Employee.class , id );
            return result;
                    
        }finally{
            em.close();
        }
    }
    
    public Employee getEmployeeByName( String name ) {
        EntityManager em = emf.createEntityManager();
        
        try{
            
            TypedQuery<Employee> query = em.createNamedQuery( "Employee.byName" , Employee.class );
            Employee result = query.setParameter( "name" , name ).getSingleResult();
            
            return result;
            
        }finally{
            em.close();
        }
    }
    
    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        
        try{
            
            TypedQuery<Employee> query = em.createNamedQuery( "Employee.all" , Employee.class );
            List<Employee> result = query.getResultList();
            
            return result;
            
        }finally{
            em.close();
        }
    }
    
    public Employee getEmployeeWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        
        try{
            
            TypedQuery<Employee> query = em.createNamedQuery( "Employee.highestSalary" , Employee.class );
            Employee result = query.getSingleResult();
            
            return result;
            
        }finally{
            em.close();
        }
    }
}
