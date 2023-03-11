package nusiss.paf.day21.Model;

import java.sql.Date;

public class Customer {
    
    private Integer id;
    private String firstName;
    private String lastName;
    private Date dob;

    // Constructor
    public Customer() {
    }

    // getters/setter
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }

    
}
