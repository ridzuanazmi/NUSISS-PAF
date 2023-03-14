package nusiss.paf.day23.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.day23.Model.Employee;
import nusiss.paf.day23.Service.EmployeeService;

@RestController
@RequestMapping(path = "/api/employees")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeSrvc;

    @PostMapping
    public ResponseEntity<Boolean> createEmp(@RequestBody Employee employee) {
        Boolean isSaved = employeeSrvc.createEmp(employee);

        return new ResponseEntity<Boolean>(isSaved, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateEmp(@RequestBody Employee employee) {
        Integer isUpdated = employeeSrvc.updateEmp(employee);

        if(isUpdated > 0) {
            return ResponseEntity.ok()
                    .body("Record successfully updated from employee table");
       } else {
            return ResponseEntity.status(HttpStatusCode.valueOf(409))
                    .body("Record update unsuccessful");
       }
    }

    @DeleteMapping(path = "/{employee-id}")
    public ResponseEntity<?> delete(@PathVariable("employee-id") Integer id) {
       Integer isDeleted = employeeSrvc.deleteEmpById(id);

       if(isDeleted > 0) {
            return ResponseEntity.ok()
                    .body("Record successfully deleted from employee table");
       } else {
            return ResponseEntity.status(HttpStatusCode.valueOf(409))
                    .body("Record deletion unsuccessful");
       }
    }

    @GetMapping
    public ResponseEntity<?> findAllEmployees() {

        List<Employee> employeeList = employeeSrvc.findAllEmployees();

        if (!employeeList.isEmpty()) {
            return ResponseEntity.ok()
                    .body(employeeList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not find Employee record");
        }
    }

    @GetMapping(path = "/dependents")
    public List<Employee> findAllEmployeesWithDependents() {
        return employeeSrvc.findAllEmployeeswithDependents();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Employee> findInnerJoinById(@PathVariable("id") Integer id) {
        Employee employee = employeeSrvc.findInnerJoinById(id);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
