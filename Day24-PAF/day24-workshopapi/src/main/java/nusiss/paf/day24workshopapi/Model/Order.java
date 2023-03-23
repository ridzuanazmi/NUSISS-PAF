package nusiss.paf.day24workshopapi.Model;

import java.sql.Date;
import java.util.List;

public class Order {
    
    private int id; 
    private Date orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private Float tax;
    private List<OrderDetail> orderDetails;

    // Constructors
    public Order() {
    }

    public Order(int id, Date orderDate, String customerName, String shipAddress, String notes, Float tax,
            List<OrderDetail> orderDetails) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.shipAddress = shipAddress;
        this.notes = notes;
        this.tax = tax;
        this.orderDetails = orderDetails;
    }

    // Getters/Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getShipAddress() {
        return shipAddress;
    }
    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Float getTax() {
        return tax;
    }
    public void setTax(Float tax) {
        this.tax = tax;
    }
    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }
    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
}
