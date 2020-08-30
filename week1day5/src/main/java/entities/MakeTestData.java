/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author riisager
 */
public class MakeTestData {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        BankCustomer b1 = new BankCustomer("Børge","Børgesen","BB001",new BigDecimal(450000),3,"We don't really like him mkay?");
        BankCustomer b2 = new BankCustomer("Lars","Tyndskid","LT002",new BigDecimal(211000),1,"Vi hjælper ham med hvidvaskning");
        BankCustomer b3 = new BankCustomer("Mette","Mogensen","MM003",new BigDecimal(311000),2,"Låner for mange penge, må ikke få flere lån");
        
        em.getTransaction().begin();
        em.persist(b1);
        em.persist(b2);
        em.persist(b3);
        em.getTransaction().commit();
        em.close();
        
    }
    
}
