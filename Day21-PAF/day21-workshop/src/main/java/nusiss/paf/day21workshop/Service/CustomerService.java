package nusiss.paf.day21workshop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.day21workshop.Model.Customer;
import nusiss.paf.day21workshop.Model.Order;
import nusiss.paf.day21workshop.Repository.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    CustomerRepository customerRepo;

    public List<Customer> getAllCustomer() {
        return customerRepo.getAllCustomer();
    }

    public List<Customer> getAllCustomerLimitOffset(Integer limit, Integer offset) {
        return customerRepo.getAllCustomerLimitOffset(limit, offset);
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerRepo.getCustomerById(id);
    }

    public Optional<List<Order>> getOrderByCustomerId(int customerId) {
        return customerRepo.getOrderByCustomerId(customerId);
    }
}
