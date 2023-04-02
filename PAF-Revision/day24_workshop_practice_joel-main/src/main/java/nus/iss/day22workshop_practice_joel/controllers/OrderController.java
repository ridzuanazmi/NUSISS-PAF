package nus.iss.day22workshop_practice_joel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import nus.iss.day22workshop_practice_joel.models.Order;
import nus.iss.day22workshop_practice_joel.models.OrderDetail;
import nus.iss.day22workshop_practice_joel.services.OrderServices;


@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @GetMapping
    public String getIndexPage(Model model, HttpSession session){

        Order order = getOrder(session);

        int nextOrderId = orderServices.getNextOrderId();
        int nextId = 1;

        OrderDetail outgoingOrderDetail = new OrderDetail();
        outgoingOrderDetail.setId(nextId);
        outgoingOrderDetail.setOrderId(nextOrderId);

        // model.addAttribute("nextid", nextId); //as hidden form field
        // model.addAttribute("nextorderid", nextOrderId); //as hidden form field
        model.addAttribute("incomingOrderDetail", outgoingOrderDetail); //for new orderdetail
        model.addAttribute("listOfOrderDetails", order.getListOfOrderDetails());


        return "index";
    }

    @PostMapping("/add")
    public String addOrderDetail(@ModelAttribute OrderDetail incomingOrderDetail, @RequestBody MultiValueMap<String,String> form, Model model, HttpSession session){
        // String nextOrderIdString = form.getFirst("orderid");
        // String nextIdString = form.getFirst("id");
        // System.out.println( "nextOrderIdString: " + nextOrderIdString);
        // System.out.println( "nextIdString: " + nextIdString);

        
        Order order = getOrder(session);

        System.out.printf(">>> Incoming OrderDetail: %s\n", incomingOrderDetail);

        int nextOrderId = incomingOrderDetail.getOrderId();
        int nextId = incomingOrderDetail.getId() + 1;

        OrderDetail outgoingOrderDetail = new OrderDetail();
        outgoingOrderDetail.setId(nextId);
        outgoingOrderDetail.setOrderId(nextOrderId);

        order.addToList(incomingOrderDetail);
        model.addAttribute("listOfOrderDetails", order.getListOfOrderDetails());
        model.addAttribute("incomingOrderDetail", outgoingOrderDetail); //for partially constructed orderdetail

        return "index";
    }

    @GetMapping("/shippingaddress")
    public String getShippingAddressPage(Model model, HttpSession session){

        if (!hasOrder(session)) {
            return "redirect:/orders";
        }

        model.addAttribute("shippingdetails", new Order()); 

        return "view2";
    }

    @PostMapping("/checkout")
    public String postCheckout(Model model, HttpSession session, @ModelAttribute Order incomingOrder){

        System.out.printf(">>> Order form: %s\n", incomingOrder);

        if (!hasOrder(session)) {
            return "redirect:/orders";
        }

        Order order = getOrder(session);
        incomingOrder.setOrderId(order.getListOfOrderDetails().get(0).getOrderId());

        //insert data to orderservice
        orderServices.insertOrder(incomingOrder);
        int i = 0;
        for (OrderDetail orderDetail : order.getListOfOrderDetails()) {
            System.out.println(++i + ". " + orderDetail);
            orderServices.insertOrderDetail(orderDetail.getOrderId(), orderDetail.getProduct(), orderDetail.getUnitPrice(), orderDetail.getDiscount(), orderDetail.getQuantity());
        }

        model.addAttribute("orderform", incomingOrder);

        session.invalidate();
        return "view3";
    }

    private Boolean hasOrder(HttpSession sess) {
		return null != sess.getAttribute("order");
	}

    private Order getOrder(HttpSession sess) {
		Order order = (Order)sess.getAttribute("order");
		if (null == order) {
			order = new Order();
			sess.setAttribute("order", order);
		}
		return order;
	}
    
    
}
