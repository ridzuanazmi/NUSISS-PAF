package nus.iss.day22workshop_practice_joel.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    private int id; //autoincrement
    private int orderId;
    private String product;
    private float unitPrice;
    private float discount;
    private int quantity;

}
