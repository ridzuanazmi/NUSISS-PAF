package nusiss.paf.day23workshop.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.day23workshop.model.Order;
import nusiss.paf.day23workshop.repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")
public class OrderAPIController {
    
    @Autowired
    OrderRepository orderRepo;

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable("id") Integer id) {

        List<Order> orderList = orderRepo.findOrderById(id); 

        if (!orderList.isEmpty()) {
            return ResponseEntity.ok()
                    .body(orderList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Order not found");
        }
    }
}
