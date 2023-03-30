package nusiss.paf.revisionday21workshop.Model;

import java.sql.Date;

public class Order {
    
    public int customerId;
    public Date orderDate;
    public Date shippedDate;
    public String shipName;
    public String shipAddress;
    public String shipCity;
    public String shipCountry;
    public String notes;

    public Order() {
    }

    public Order(int customerId, Date orderDate, Date shippedDate, String shipName, String shipAddress,
            String shipCity, String shipCountry, String notes) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipCountry = shipCountry;
        this.notes = notes;
    }
    
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public Date getShippedDate() {
        return shippedDate;
    }
    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }
    public String getShipName() {
        return shipName;
    }
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
    public String getShipAddress() {
        return shipAddress;
    }
    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }
    public String getShipCity() {
        return shipCity;
    }
    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }
    public String getShipCountry() {
        return shipCountry;
    }
    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Order [customerId=" + customerId + ", orderDate=" + orderDate + ", shippedDate=" + shippedDate
                + ", shipName=" + shipName + ", shipAddress=" + shipAddress + ", shipCity=" + shipCity
                + ", shipCountry=" + shipCountry + ", notes=" + notes + "]";
    }
    
}
