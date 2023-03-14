package nusiss.paf.day23.Model;

import java.util.List;

public class Employee {
    
    private int id;
    // firstName (Entity) --> first_name (mySQL)
    // firstname (Entity) --> firstname(mySQL)
    private String firstName;
    private String lastName;
    private Integer salary;
    private List<Dependent> dependents; // One employee can have many dependents

    // Empty Constructor
    public Employee() {
    }

    // Full Constructor
    public Employee(int id, String firstName, String lastName, Integer salary, List<Dependent> dependents) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.dependents = dependents;
    }


    // Getters/Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    public Integer getSalary() {
        return salary;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    public List<Dependent> getDependents() {
        return dependents;
    }
    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }

    
}
