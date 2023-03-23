package nusiss.paf.day24workshopapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nusiss.paf.day24workshopapi.Model.Order;
import nusiss.paf.day24workshopapi.Service.OrderDetailsService;

@Controller
@RequestMapping("/orders")
public class OrderDetailController {
    
    @Autowired
    private OrderDetailsService oDSrvc;

    @GetMapping
    public String getAllOrderWithDetails(Model model) {
        List<Order> orderList = oDSrvc.getAllOrderWithDetails();
        model.addAttribute("order", orderList);
        return "orderList";
    }
}
