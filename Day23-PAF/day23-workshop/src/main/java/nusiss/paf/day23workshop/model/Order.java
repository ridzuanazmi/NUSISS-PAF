package nusiss.paf.day23workshop.model;

import java.sql.Date;

public class Order {
    
    private int orderId;
    private Date orderDate;
    private int customerId;
    private int productId;
    private Float totalPrice;
    private Float costPrice;

    // constructors
    public Order() {
    }
    
    public Order(int orderId, Date orderDate, int customerId, int productId, Float totalPrice, Float costPrice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.productId = productId;
        this.totalPrice = totalPrice;
        this.costPrice = costPrice;
    }
    
    // getters/setters
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public Float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Float getCostPrice() {
        return costPrice;
    }
    public void setCostPrice(Float costPrice) {
        this.costPrice = costPrice;
    }

    
}
