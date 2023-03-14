package nusiss.paf.day23workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nusiss.paf.day23workshop.model.Order;
import nusiss.paf.day23workshop.payload.SearchRequest;
import nusiss.paf.day23workshop.repository.OrderRepository;
import nusiss.paf.day23workshop.service.OrderRestTemplateService;

@Controller
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderRestTemplateService orts;

    @GetMapping
    public String searchOrder(Model model) {

        SearchRequest sr = new SearchRequest();
        model.addAttribute("searchObject", sr);
        
        return "searchOrder";
    }

    @PostMapping("/search")
    public String processSearch(@ModelAttribute("searchObject") SearchRequest sr, Model model, BindingResult result) {
        
        List<Order> orderList = orts.findOrderById(sr.getOrderId());
        model.addAttribute("orders", orderList);

        return "orderList";
    }
}
