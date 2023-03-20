package nusiss.paf.day21revision.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.day21revision.Model.Customer;
import nusiss.paf.day21revision.Model.Order;
import nusiss.paf.day21revision.Repository.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    CustomerRepository customerRepo;

    public List<Customer> getAllCustomers() {
        return customerRepo.getAllCustomers();
    }

    public List<Customer> getLimitCustomer(int limit, int offset) {
        return customerRepo.getLimitCustomers(limit, offset);
    }

    public List<Order> getOrderByCustId(Integer id) {
        return customerRepo.getOrderByCustId(id);
    }
}
