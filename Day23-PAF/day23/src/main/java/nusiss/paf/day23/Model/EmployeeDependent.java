package nusiss.paf.day23.Model;

public class EmployeeDependent {
    private Employee employee;
    private Dependent dependent;

    public EmployeeDependent(Employee employee, Dependent dependent) {
        this.employee = employee;
        this.dependent = dependent;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Dependent getDependent() {
        return dependent;
    }
}
