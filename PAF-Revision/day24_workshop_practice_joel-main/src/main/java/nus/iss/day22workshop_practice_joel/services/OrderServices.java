package nus.iss.day22workshop_practice_joel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.day22workshop_practice_joel.models.Order;
import nus.iss.day22workshop_practice_joel.repositories.OrderRepository;

@Service
public class OrderServices {

    @Autowired
    private OrderRepository orderRepository;

    public int insertOrder(Order order) {
        return orderRepository.insertOrder(order);
    }

    public Boolean insertOrderDetail(int orderId, String product, float unitPrice, float discount, int quantity) {
        return orderRepository.insertOrderDetail(orderId, product, unitPrice, discount, quantity);
    }

    public int getNextOrderId() {
        return orderRepository.getNextOrderId();
    }




    
    
}
