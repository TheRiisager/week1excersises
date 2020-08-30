package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Tester {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();
            
            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            for ( Employee e : getAll100k() ) {
            System.out.print( e.getFirstName() );
            }
            
            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            System.out.print( getKlo999() );
            
            //3) Create a query to fetch the highest salary and print the value
            System.out.print( getHighestSalary() );

            //4) Create a query to fetch the firstName of all Employees and print the names
           
            //5 Create a query to calculate the number of employees and print the number
            
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            
        } finally {
            em.close();
            emf.close();
        }
    }
    
    
    private static List<Employee> getAll100k(){
        EntityManager em = emf.createEntityManager();
        
        try{
            Query query = em.createQuery( "SELECT e from Employee e WHERE e.salary > 100000" );

            return query.getResultList();
        } finally {
            em.close();
        }
        
    }
    
    private static String getKlo999(){
        EntityManager em = emf.createEntityManager();
        
        try{
            TypedQuery query = em.createQuery( "SELECT e.firstName FROM Employee e WHERE e.id = 'klo999'" , String.class );

            return query.getSingleResult().toString();
        } finally{
            em.close();
        }
    }
    
    private static BigDecimal getHighestSalary(){
        EntityManager em = emf.createEntityManager();
        
        try{
            
            TypedQuery query = em.createQuery("SELECT MAX( e.salary ) FROM Employee e" , BigDecimal.class);
            
            return (BigDecimal) query.getSingleResult();
            
        }finally{
            em.close();
        }
    }

}
