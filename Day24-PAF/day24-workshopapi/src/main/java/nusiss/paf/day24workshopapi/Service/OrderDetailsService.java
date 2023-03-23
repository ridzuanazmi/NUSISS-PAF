package nusiss.paf.day24workshopapi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.day24workshopapi.Model.Order;
import nusiss.paf.day24workshopapi.Repository.OrderDetailsRepository;

@Service
public class OrderDetailsService {
    
    @Autowired
    private OrderDetailsRepository oDRepo;

    public List<Order> getAllOrderWithDetails() {
        return oDRepo.getAllOrderWithDetails();
    }
}
