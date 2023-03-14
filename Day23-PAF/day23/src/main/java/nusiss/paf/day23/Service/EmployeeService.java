package nusiss.paf.day23.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.day23.Model.Employee;
import nusiss.paf.day23.Repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository employeeRepo;

    public Boolean createEmp(Employee employee) {
        return employeeRepo.createEmp(employee);
    }

    public int updateEmp(Employee employee) {
        return employeeRepo.updateEmp(employee);
    }

    public int deleteEmpById(Integer id) {
        return employeeRepo.deleteEmpById(id);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAllEmployees();
    }

    public List<Employee> findAllEmployeeswithDependents() {
        return employeeRepo.findAllWithDep2();
    }

    public Employee findInnerJoinById(Integer id) {
        return employeeRepo.findInnerJoinById(id);
    }
}
