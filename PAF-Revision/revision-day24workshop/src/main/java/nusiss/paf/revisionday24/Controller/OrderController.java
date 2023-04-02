package nusiss.paf.revisionday24.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import nusiss.paf.revisionday24.Model.Order;
import nusiss.paf.revisionday24.Model.OrderDetails;
import nusiss.paf.revisionday24.Repository.OrderRepository;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;

    @GetMapping(path = "/order")
    public String showOrder(Model model) {

        List<Order> orderList = orderRepo.selectOrders();
        List<OrderDetails> odList = orderRepo.selectOrderDetails();
        model.addAttribute("order", orderList);
        model.addAttribute("orderDetails", odList);
        return "view1";
    }

    @GetMapping(path = "/orderform")
    public String orderForm(Model model, HttpSession session) {

        Order order = getOrder(session);
        System.out.printf(">>>> GET /orderform SESSION: %s\n", order.toString());
        model.addAttribute("order", order);
        return "view2";
    }

    @PostMapping(path = "/create-order")
    public String createOrder(
            @ModelAttribute Order order,
            Model model,
            HttpSession session) {

        // Adds the order into orders table
        Boolean isCreated = orderRepo.createOrder(order);

        // How do I add order into session?

        if (isCreated) {
            // Get the order id and set it in Order and OrderDetails
            Integer nextId = orderRepo.getLatestOrderId();
            order.setId(nextId);
            OrderDetails od = new OrderDetails();
            od.setOrderId(nextId);

            // Add the order object to the session
            session.setAttribute("order", order);

            System.out.printf(">>>> POST /create-order %s\n", order.toString());
            System.out.printf(">>>> POST /create-order ID = %d\n", nextId);

            model.addAttribute("od", od);
            model.addAttribute("listOfOd", order.getOdList());
            return "view3";
        } else {
            return "error";
        }
    }

    @PostMapping(path = "/add-order-details")
    public String createOrderDetails(
            @ModelAttribute OrderDetails od,
            Model model,
            HttpSession session) {

        Order order = getOrder(session);
        order.addToList(od);
        System.out.printf(">>>> POST /add-order-details: %s\n", order.toString());

        model.addAttribute("listOfOd", order.getOdList());
        model.addAttribute("od", od);

        return "view3";
    }

    @GetMapping(path = "/checkout")
    public String checkout(
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {

        Order order = getOrder(session);
        System.out.printf(">>>> GET /checkout Order %s\n", order.toString());
        orderRepo.batchInsertOrderDetails(order.getId(), order.getOdList());
        session.invalidate();

        // Add a flash attribute indicating a successful checkout
        redirectAttributes.addFlashAttribute("checkoutSuccess", true);
        
        return "redirect:/order";
    }

    // Session methods
    private Order getOrder(HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        if (null == order) {
            order = new Order();
            session.setAttribute("order", order);
        }
        return order;
    }
}
