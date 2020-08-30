package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
    @NamedQuery(name="Employee.byName",
                query="SELECT e FROM Employee e WHERE e.name = :name"),
    @NamedQuery(name="Employee.all",
                query="SELECT e FROM Employee e"),
    @NamedQuery(name="Employee.highestSalary",
                query="SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)")
})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String address;
    private BigDecimal salary;

    public Employee() {
    }
    
    public Employee( String name , String address , BigDecimal salary ){
        this.name = name;
        this.address = address;
        this.salary = salary;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    
    
    
}
