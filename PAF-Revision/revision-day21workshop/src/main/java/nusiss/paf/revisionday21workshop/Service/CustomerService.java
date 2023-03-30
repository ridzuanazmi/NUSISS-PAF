package nusiss.paf.revisionday21workshop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import nusiss.paf.revisionday21workshop.Model.Customer;
import nusiss.paf.revisionday21workshop.Model.Order;
import nusiss.paf.revisionday21workshop.Repository.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository custRepo;

    public List<Customer> getAllCustomers(Integer limit, Integer offset) {
        return custRepo.getAllCustomers(limit, offset);
    }

    public Optional<Customer> getCustomerById(Integer id) {
        try {
            return custRepo.getCustomerById(id);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Order> getOrderById(Integer id) {
        return custRepo.getOrderById2(id);
    }
}
