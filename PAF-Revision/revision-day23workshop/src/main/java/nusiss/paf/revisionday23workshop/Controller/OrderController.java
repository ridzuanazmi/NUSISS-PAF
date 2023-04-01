package nusiss.paf.revisionday23workshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nusiss.paf.revisionday23workshop.Model.Order;
import nusiss.paf.revisionday23workshop.Service.OrderService;

@Controller
public class OrderController {
    
    @Autowired
    private OrderService orderSrvc;

    @GetMapping(path = "/order/total/{order_id}")
    public String getOrderById(@PathVariable("order_id") Integer id, Model model) {

        List<Order> orderList = orderSrvc.getOrderById(id);
        System.out.printf(">>>> ORDER %d LIST: \n%s\n",id,orderList);
        model.addAttribute("orders", orderList);

        return "view1";
    }
}
