package facades;


import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public BankCustomer addCustomer( BankCustomer b ) {
        EntityManager em = emf.createEntityManager();
        
        try{
            
            em.getTransaction().begin();
            em.persist( b );
            em.getTransaction().commit();
            
            return b;
            
        }finally{
            em.close();
        }
        
    }
    
    public List<BankCustomer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        
        try{
            
            TypedQuery<BankCustomer> query = em.createNamedQuery( "BankCustomer.getAll" , BankCustomer.class );
            return query.getResultList();
            
        }finally{
            em.close();
        }
        
    }
    
    public CustomerDTO getCustomerByID( int id ){
        EntityManager em = emf.createEntityManager();
        CustomerDTO result;
        
        try{
            
            TypedQuery<BankCustomer> query = em.createNamedQuery( "BankCustomer.getByID", BankCustomer.class );
            result = new CustomerDTO( query.setParameter( "id" , id ).getSingleResult() );
            
            return result;
            
        }finally{
            em.close();
        }
    }
    
    public List<CustomerDTO> getCustomerByName( String name ){
        EntityManager em = emf.createEntityManager();
        ArrayList<CustomerDTO> result = new ArrayList<CustomerDTO>();
        
        try{
            
            TypedQuery<BankCustomer> query = em.createNamedQuery( "BankCustomer.getByName", BankCustomer.class );
            
            for( BankCustomer b : query.setParameter( "name" , name ).getResultList() ) {
            
                result.add( new CustomerDTO(b) );
            
            }
            
            return result;
            
        }finally{
            em.close();
        }
    }

}
