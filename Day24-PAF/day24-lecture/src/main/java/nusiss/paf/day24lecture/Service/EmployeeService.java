package nusiss.paf.day24lecture.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.day24lecture.Model.Employee;
import nusiss.paf.day24lecture.Repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository employeeRepo;

    public List<Employee> findAllEmp() {
        return employeeRepo.findAllEmp();
    }

    public Employee findEmpById(Integer id) {
        return employeeRepo.findEmpById(id);
    }

    public Boolean createEmp(Employee emp) {
        return employeeRepo.createEmp(emp);
    }

    public Boolean updateEmp1(Employee emp) {
        return employeeRepo.updateEmp1(emp);
    }

    public Integer updateEmp2(Employee emp) {
        return employeeRepo.updateEmp2(emp);
    }

    public Boolean deleteEmp(Integer id) {
        return null;
    }
}
