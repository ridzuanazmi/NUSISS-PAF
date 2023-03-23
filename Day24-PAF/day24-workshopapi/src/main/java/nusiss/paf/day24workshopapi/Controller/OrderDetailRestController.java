package nusiss.paf.day24workshopapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.day24workshopapi.Model.Order;
import nusiss.paf.day24workshopapi.Service.OrderDetailsService;

@RestController
@RequestMapping("/api/orders")
public class OrderDetailRestController {
    
    @Autowired
    private OrderDetailsService oDSrvc;

    @GetMapping
    public ResponseEntity<?> getAllOrderWithDetails() {

        List<Order> orderList = oDSrvc.getAllOrderWithDetails();

        if (!orderList.isEmpty()) {
            return ResponseEntity.ok()
                    .body(orderList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order List could not be found");
        }
    }
}
