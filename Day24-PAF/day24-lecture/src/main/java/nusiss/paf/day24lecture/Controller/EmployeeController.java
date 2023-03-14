package nusiss.paf.day24lecture.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nusiss.paf.day24lecture.Model.Employee;
import nusiss.paf.day24lecture.Service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeSrvc;

    // Displays all employee records
    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> empList = employeeSrvc.findAllEmp();

        model.addAttribute("employees", empList);

        return "employeeList";
    }

    // displayes a form to add new employee record
    @GetMapping("/addNew")
    public String createEmp(Model model) {
        Employee empForm = new Employee();
        model.addAttribute("employee", empForm);

        return "addNewEmployee";
    }

    // method to create employee record in the get mapping
    @PostMapping("/create")
    public String createEmp(@ModelAttribute("employee") Employee empForm, BindingResult result) {

        if (result.hasErrors()) {
            return "addNew";
        }

        Employee newEmployee = new Employee();
        newEmployee.setFirstName(empForm.getFirstName());
        newEmployee.setLastName(empForm.getLastName());
        newEmployee.setSalary(empForm.getSalary());
        employeeSrvc.createEmp(newEmployee);

        return "redirect:/employeeList";
    }

    // It is a hyperlink that uses get method by dafult so we call the method using get mapping and redirect to the employeelist page
    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeSrvc.deleteEmp(id);
        return "redirect:/employeeList";
    }

    @GetMapping("/update/{id}")
    public String updateEmp(@PathVariable("id") Integer id, Model model) {
        Employee retrieveEmp = employeeSrvc.findEmpById(id);

        model.addAttribute("empForm", retrieveEmp);
        return "updateEmployee";
    }

    @PostMapping
    public String saveUpdateEmployee(@ModelAttribute("empForm") Employee employee, BindingResult result) {

        if (result.hasErrors()) {
            return "updateEmployee";
        }

        employeeSrvc.updateEmp2(employee);
        return "redirect:/employeeList";
    }

}
