package nusiss.paf.day21.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.day21.Repository.CustomerRepository;
import nusiss.paf.day21.Model.Customer;

@Service
public class CustomerService {
    
    @Autowired
    CustomerRepository customerRepo;

    public List<Customer> getAllCustomer() {
        return customerRepo.getAllCustomer();
    }

    public List<Customer> getAllCustomerLimitOffset(int limit, int offset) {
        return customerRepo.getAllCustomerLimitOffset(limit, offset);
    }

    public Customer getCustomerById(int id) {
        return customerRepo.getCustomerById(id);
    }
}
