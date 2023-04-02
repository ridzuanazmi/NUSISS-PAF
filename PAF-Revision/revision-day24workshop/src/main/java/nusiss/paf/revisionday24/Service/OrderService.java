package nusiss.paf.revisionday24.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.revisionday24.Model.Order;
import nusiss.paf.revisionday24.Repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepo;

    public Optional<Order> selectOrderById(Integer id) {
        try {
            return orderRepo.selectOrderById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
