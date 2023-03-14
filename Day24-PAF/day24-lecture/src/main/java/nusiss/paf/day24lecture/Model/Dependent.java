package nusiss.paf.day24lecture.Model;

import java.sql.Date;

public class Dependent {
    
    private int id;
    private int employeeId;
    private String fullName;
    private String relationship;
    private Date birthDate;
    private Employee employee; // one dependent have one employee

    // Empty constructor
    public Dependent() {
    }

    // full constructor
    // public Dependent(int id, int employeeId, String fullName, Date birthDate, String relationship) {
    //     this.id = id;
    //     this.employeeId = employeeId;
    //     this.fullName = fullName;
    //     this.birthDate = birthDate;
    //     this.relationship = relationship;
    // }

    // Getters/Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getRelationship() {
        return relationship;
    }
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    
}
