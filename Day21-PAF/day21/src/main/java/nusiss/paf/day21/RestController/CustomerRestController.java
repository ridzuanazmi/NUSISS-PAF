package nusiss.paf.day21.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.day21.Service.CustomerService;
import nusiss.paf.day21.Model.Customer;

@RestController
@RequestMapping(path = "/api/customer")
public class CustomerRestController {
    
    @Autowired
    CustomerService customerSrvc;

    @GetMapping
    public List<Customer> getAllCustomer() {
        return customerSrvc.getAllCustomer();
    }

    @GetMapping("/limit")
    public List<Customer> getAllCustomerLimitOffset(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        return customerSrvc.getAllCustomerLimitOffset(limit, offset);
    }

    @GetMapping("/id")
    public Customer getCustomerById(@RequestParam("id") int id) {
        return customerSrvc.getCustomerById(id);
    }
}
