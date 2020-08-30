/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author riisager
 */
public class EntityTested {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Customer c1 = new Customer("Lars","Tyndskid");
        Customer c2 = new Customer("Anders","Andersen");
        
        em.getTransaction().begin();
        
        em.persist(c1);
        em.persist(c2);
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
    }
    
}
