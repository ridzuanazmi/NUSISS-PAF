package nusiss.paf.revisionday23workshop.Model;

import java.sql.Date;

public class Order {
    
    private int orderId;
    private int customerId;
    private int productId;
    private Date orderDate;
    private Float totalPrice;
    private Float costPrice;

    public Order() {
    }

    public Order(int orderId, int customerId, int productId, Date orderDate, Float totalPrice, Float costPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.costPrice = costPrice;
    }
    
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", customerId=" + customerId + ", productId=" + productId + ", orderDate="
                + orderDate + ", totalPrice=" + totalPrice + ", costPrice=" + costPrice + "]";
    }

    
}
