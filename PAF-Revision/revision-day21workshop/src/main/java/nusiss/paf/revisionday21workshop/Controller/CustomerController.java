package nusiss.paf.revisionday21workshop.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nusiss.paf.revisionday21workshop.Model.Customer;
import nusiss.paf.revisionday21workshop.Model.Order;
import nusiss.paf.revisionday21workshop.Service.CustomerService;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService custSrvc;

    // GET /api/customers
    // Accept: application/json
    @GetMapping(path = "/api/customers")
    @ResponseBody
    public ResponseEntity<?> getAllCustomers(
            @RequestParam(value = "offset", defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", defaultValue = "5") Integer limit) {

        List<Customer> customerList = custSrvc.getAllCustomers(limit, offset);

        System.out.printf(">>>> %s\n", customerList);

        if (!customerList.isEmpty()) {
            return ResponseEntity.ok()
                    .body(customerList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not find Customer List");
        }
    }

    // GET /api/customer/<customer_id>
    // Accept: application/json
    @GetMapping(path = "/api/customer/{customer_id}")
    @ResponseBody
    public ResponseEntity<?> getCustomerById(
            @PathVariable("customer_id") Integer id) {

        Optional<Customer> customer = custSrvc.getCustomerById(id);

        if (!customer.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer ID does not exist");
        } else {
            System.out.printf(">>>> %s\n", customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

    // GET /api/customer/<customer_id>/orders
    // Accept: application/json
    @GetMapping(path = "/api/customer/{customer_id}/orders")
    @ResponseBody
    public ResponseEntity<?> getOrderById(
            @PathVariable("customer_id") Integer id) {

        Optional<Customer> customer = custSrvc.getCustomerById(id);

        if (!customer.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer ID does not exist");
        } else {
            List<Order> orderList = custSrvc.getOrderById(id);
            System.out.printf(">>>> %s\n", orderList);
            return new ResponseEntity<>(orderList, HttpStatus.OK);
        }
    }
}
