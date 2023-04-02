package nus.iss.day22workshop_practice_joel.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private List<OrderDetail> listOfOrderDetails = new ArrayList<OrderDetail>();

    private int orderId; //autoincrement
    private Date orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private Float tax;

    public void addToList(OrderDetail orderDetail){
        listOfOrderDetails.add(orderDetail);
    }
    
}
