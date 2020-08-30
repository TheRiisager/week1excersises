/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author riisager
 */
public class FacadeExampleTest {
    
    private static EntityManagerFactory emf;
    private static FacadeExample facade;
    
    
    
    @BeforeAll
    public static void setupTest(){
        emf = Persistence.createEntityManagerFactory("pu");
        facade = FacadeExample.getFacadeExample(emf);
    }
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM BankCustomer").executeUpdate();
            
            
            BankCustomer b1 = new BankCustomer("Børge","Børgesen","BB001",new BigDecimal(450000),3,"We don't really like him mkay?");
            BankCustomer b2 = new BankCustomer("Lars","Tyndskid","LT002",new BigDecimal(211000),1,"Vi hjælper ham med hvidvaskning");
            BankCustomer b3 = new BankCustomer("Mette","Mogensen","MM003",new BigDecimal(311000),2,"Låner for mange penge, må ikke få flere lån");
           
           
            em.persist( b1 );
            em.persist( b2 );
            em.persist( b3 );
            

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @Test
    public void testGetAllCustomers(){
        
        List<BankCustomer> customerList = facade.getAllCustomers();
        
        //tests that list is not empty
        org.junit.jupiter.api.Assertions.assertFalse( customerList.isEmpty() );
        
        for( BankCustomer b : customerList ) {
            
            //Netbeans gider ikke anerkende at assertions er importeret, så jeg bliver nødt til at skrve det sådan her.
            //Test that each entry is an instance of BankCustomer containing data
            org.junit.jupiter.api.Assertions.assertTrue(b instanceof BankCustomer );
            org.junit.jupiter.api.Assertions.assertNotNull( b.getId() );
            org.junit.jupiter.api.Assertions.assertNotNull( b.getFirstName() );
            org.junit.jupiter.api.Assertions.assertNotNull( b.getLastName() );
            org.junit.jupiter.api.Assertions.assertNotNull( b.getBalance() );
            org.junit.jupiter.api.Assertions.assertNotNull( b.getCustomerRanking() );
            org.junit.jupiter.api.Assertions.assertNotNull( b.getInternalInfo() );
        }
        
        
        
    }
    
    @Test
    public void testGetCustomerByID() {
        
        CustomerDTO test1 = facade.getCustomerByID(1);
        
        //Test if name matches
        org.junit.jupiter.api.Assertions.assertEquals( test1.getFullName() , "Mette Mogensen" );
        
        //Test if account number matches
        org.junit.jupiter.api.Assertions.assertEquals( test1.getAccountNumber() , "MM003" );
    
    }
    
    @Test
    public void testGetCustomerByName() {
        
        List<CustomerDTO> resultList = facade.getCustomerByName("Lars");
        
        //Test that list is not empty, and is the expected size (1)
        org.junit.jupiter.api.Assertions.assertFalse( resultList.isEmpty() );
        org.junit.jupiter.api.Assertions.assertEquals( resultList.size() , 1);
        
        //Test that the person is the expected one
        org.junit.jupiter.api.Assertions.assertEquals( resultList.get(0).getFullName() , "Lars Tyndskid");
        
    }
    
}
