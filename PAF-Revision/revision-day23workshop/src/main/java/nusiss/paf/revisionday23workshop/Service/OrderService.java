package nusiss.paf.revisionday23workshop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.revisionday23workshop.Model.Order;
import nusiss.paf.revisionday23workshop.Repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepo;

    public List<Order> getOrderById(Integer id) {
        return orderRepo.getOrderById(id);
    }
}
