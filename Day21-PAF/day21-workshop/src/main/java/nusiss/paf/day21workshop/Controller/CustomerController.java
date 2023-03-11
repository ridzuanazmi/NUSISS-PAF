package nusiss.paf.day21workshop.Controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.day21workshop.Model.Customer;
import nusiss.paf.day21workshop.Model.Order;
import nusiss.paf.day21workshop.Model.Error.ErrorResponse;
import nusiss.paf.day21workshop.Service.CustomerService;

@RestController
@RequestMapping(path = "/api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerSrvc;

    private Logger logger = Logger.getLogger(CustomerController.class.getName());

    // GET/api/customers
    // Accept: application/json
    //
    // get list of all customers, support the following parameters:
    // offset - return the first result from n records from the first; n is the
    // number given by offset parameter limit - return the number of records
    // specified by limit.
    // The default value for offset is 0 and limit is 5.
    @GetMapping
    public List<Customer> getAllCustomer(
            @RequestParam(defaultValue = "5", required = false) Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer offset) {

        if (null == limit && null == offset)
            return customerSrvc.getAllCustomer();

        return customerSrvc.getAllCustomerLimitOffset(limit, offset);
    }

    // Get /api/customer/customer/<customer_id>
    // Accept: application/json
    // Code below returns a json string instead of the object and could send an
    // error message
    // @GetMapping("/{id}")
    // public ResponseEntity<String> getCustomerById(@PathVariable(required = false)
    // int id) {

    // Optional<Customer> customerOpt = customerSrvc.getCustomerById(id);

    // if (customerOpt.isPresent()) {
    // Customer customer = customerOpt.get();
    // return ResponseEntity.ok()
    // .contentType(MediaType.APPLICATION_JSON)
    // .body(customer.toJson().toString());
    // } else {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer ID not
    // found");
    // }
    // }

    // returns an object instead of a string but could not send a message
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable(required = false) int id) {

        Optional<Customer> customerOpt = customerSrvc.getCustomerById(id);

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(customer);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Customer not found", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<?> getOrderByCustomerId(@PathVariable(required = false) int customerId) {

        Optional<List<Order>> orderOpt = customerSrvc.getOrderByCustomerId(customerId);

        logger.info(">>>>%s".formatted(orderOpt.toString()));

        // Yes, Optional[[]] means that the Optional instance is not null, but it
        // contains an empty array. In this case, the getOrderByCustomerId() method
        // returned an Optional instance with an empty array as its value
        if (orderOpt.isPresent()) {
            List<Order> orderList = orderOpt.get();
            if (!orderList.isEmpty()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(orderList);
            }
        }
        ErrorResponse errorResponse = new ErrorResponse("Order not found",
                                            HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);

    }

    // @GetMapping("/{customerId}/orders")
    // public ResponseEntity<?> getOrderByCustomerId(@PathVariable(required = false)
    // int customerId) {
    // Optional<List<Order>> orderOpt =
    // customerSrvc.getOrderByCustomerId(customerId);

    // if (orderOpt.isPresent()) {
    // List<Order> orderList = orderOpt.get();

    // if (orderList.isEmpty()) {
    // ErrorResponse errorResponse = new ErrorResponse("No orders found for customer
    // ID: ",
    // HttpStatus.NOT_FOUND.value());
    // return ResponseEntity.status(HttpStatus.NOT_FOUND)
    // .contentType(MediaType.APPLICATION_JSON)
    // .body(errorResponse);
    // } else {
    // return ResponseEntity.ok()
    // .contentType(MediaType.APPLICATION_JSON)
    // .body(orderList);
    // }
    // } else {
    // ErrorResponse errorResponse = new ErrorResponse("Customer not found with ID:
    // ",
    // HttpStatus.NOT_FOUND.value());
    // return ResponseEntity.status(HttpStatus.NOT_FOUND)
    // .contentType(MediaType.APPLICATION_JSON)
    // .body(errorResponse);
    // }
    // }

}
