package nusiss.paf.day24lecture.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import nusiss.paf.day24lecture.Model.Employee;

@Repository
public class EmployeeRepository {

    RestTemplate restTemplate = new RestTemplate();

    private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://localhost:8080/api/employees";
    private static final String GET_EMPLOYEEBYID_ENDPOINT_URL = "http://localhost:8080/api/employees/{id}";
    private static final String CREATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/employees";
    private static final String PUT_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/employees";
    private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/employees/{id}";

    // Method to find all the employee records
    public List<Employee> findAllEmp() {

        ResponseEntity<List<Employee>> empList = restTemplate.exchange(GET_EMPLOYEES_ENDPOINT_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Employee>>() {
                });
        return empList.getBody();
    }

    public Employee findEmpById(Integer id) {
        Employee employee = restTemplate.getForObject(GET_EMPLOYEEBYID_ENDPOINT_URL, Employee.class,
                Map.of("id", id));
        return employee;
    }

    public Boolean createEmp(Employee emp) {
        Boolean isCreated = restTemplate.postForObject(CREATE_EMPLOYEE_ENDPOINT_URL, emp, Boolean.class);
        return isCreated;
    }

    // This method is different from darryl's
    public Boolean updateEmp1(Employee emp) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Employee> request = new HttpEntity<>(emp, headers);
        ResponseEntity<Employee> response = restTemplate.exchange(PUT_EMPLOYEE_ENDPOINT_URL, HttpMethod.PUT, request, 
                                Employee.class, emp.getId());
        return response.getStatusCode() == HttpStatus.OK;
    }

    public Integer updateEmp2(Employee emp) {

        restTemplate.put(PUT_EMPLOYEE_ENDPOINT_URL, emp);
        return 1;
    }

    // Delete Employee method
    public Boolean deleteEmp(Integer id) {
        return null;
    }
}
