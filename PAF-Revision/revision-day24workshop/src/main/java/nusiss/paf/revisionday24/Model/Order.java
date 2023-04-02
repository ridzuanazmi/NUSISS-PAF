package nusiss.paf.revisionday24.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private Date orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private Float tax;
    private List<OrderDetails> odList = new ArrayList<>();

    public Order() {
    }

    public Order(int id, Date orderDate, String customerName, String shipAddress, String notes, Float tax,
            List<OrderDetails> odList) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.shipAddress = shipAddress;
        this.notes = notes;
        this.tax = tax;
        this.odList = odList;
    }

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

    public List<OrderDetails> getOdList() {
        return odList;
    }

    public void setOdList(List<OrderDetails> odList) {
        this.odList = odList;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", orderDate=" + orderDate + ", customerName=" + customerName + ", shipAddress="
                + shipAddress + ", notes=" + notes + ", tax=" + tax + ", odList=" + odList + "]";
    }

    // ADD TO LIST METHOD
    public void addToList(OrderDetails orderDetails) {
        List<OrderDetails> found = this.odList.stream()
                .filter(od -> od.getProduct().equals(orderDetails.getProduct()))
                .toList();
        if (found.isEmpty())
            this.odList.add(orderDetails);
        else
            found.get(0).add(orderDetails.getQuantity());
    }
}
