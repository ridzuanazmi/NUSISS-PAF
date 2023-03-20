package nusiss.paf.day21revision.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.day21revision.Model.Customer;
import nusiss.paf.day21revision.Model.Order;
import nusiss.paf.day21revision.Service.CustomerService;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerSrvc;

    @GetMapping
    @RequestMapping("/api/customers")
    public ResponseEntity<?> getAllCustomers() {

        List<Customer> customerList = customerSrvc.getAllCustomers();

        if (!customerList.isEmpty()) {
            return ResponseEntity.ok()
                    .body(customerList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No customer list found");
        }
    }

    @GetMapping("/api/customer/limit")
    public ResponseEntity<?> getLimitCustomers(
            @RequestParam(defaultValue = "5", required = false) Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer offset) {

        List<Customer> customerList = new ArrayList<>();
        
        if (null == limit & null == offset) {
            customerList = customerSrvc.getAllCustomers();
        } else {
            customerList = customerSrvc.getLimitCustomer(limit, offset);
        }

        if (!customerList.isEmpty()) {
            return ResponseEntity.ok()
                    .body(customerList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No customer list found");
        }
    }

    @GetMapping
    @RequestMapping("/api/customer/{customer_id}/orders")
    public ResponseEntity<?> getOrderByCustId(@PathVariable("customer_id") Integer id) {

        List<Order> orderList = customerSrvc.getOrderByCustId(id);

        if (!orderList.isEmpty()) {
            return ResponseEntity.ok()
                    .body(orderList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order list empty or customer id invalid");
        }
    }
}
