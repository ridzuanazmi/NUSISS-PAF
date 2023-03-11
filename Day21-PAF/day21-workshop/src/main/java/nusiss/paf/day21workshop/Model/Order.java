package nusiss.paf.day21workshop.Model;

import java.sql.Date;

public class Order {

    // fields not including the date variable
    private int id;
    private int employeeId;
    private int customerId;
    private Date orderDate;
    private Date shippedDate;
    private int shipperId;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipState;
    private String shipZipCode;
    private String shipCountry;
    private int shipFee;
    private int taxes;
    private String paymentType;
    private Date paidDate;
    private String notes;
    private float taxRate;
    private int taxStatus;
    private int StatusId;

    // Empty constructor
    public Order() {
    }

    // Full constructor
    public Order(int id, int employeeId, int customerId, Date orderDate, Date shippedDate, int shipperId,
            String shipName, String shipAddress, String shipCity, String shipState, String shipZipCode,
            String shipCountry, int shipFee, int taxes, String paymentType, Date paidDate, String notes, float taxRate,
            int taxStatus, int statusId) {
        this.id = id;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.shipperId = shipperId;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipState = shipState;
        this.shipZipCode = shipZipCode;
        this.shipCountry = shipCountry;
        this.shipFee = shipFee;
        this.taxes = taxes;
        this.paymentType = paymentType;
        this.paidDate = paidDate;
        this.notes = notes;
        this.taxRate = taxRate;
        this.taxStatus = taxStatus;
        StatusId = statusId;
    }

    // getters/setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
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

    public String getShipState() {
        return shipState;
    }

    public void setShipState(String shipState) {
        this.shipState = shipState;
    }

    public String getShipZipCode() {
        return shipZipCode;
    }

    public void setShipZipCode(String shipZipCode) {
        this.shipZipCode = shipZipCode;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public int getShipFee() {
        return shipFee;
    }

    public void setShipFee(int shipFee) {
        this.shipFee = shipFee;
    }

    public int getTaxes() {
        return taxes;
    }

    public void setTaxes(int taxes) {
        this.taxes = taxes;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public float getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(float taxRate) {
        this.taxRate = taxRate;
    }

    public int getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(int taxStatus) {
        this.taxStatus = taxStatus;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", employeeId=" + employeeId + ", customerId=" + customerId + ", orderDate="
                + orderDate + ", shippedDate=" + shippedDate + ", shipperId=" + shipperId + ", shipName=" + shipName
                + ", shipAddress=" + shipAddress + ", shipCity=" + shipCity + ", shipState=" + shipState
                + ", shipZipCode=" + shipZipCode + ", shipCountry=" + shipCountry + ", shipFee=" + shipFee + ", taxes="
                + taxes + ", paymentType=" + paymentType + ", paidDate=" + paidDate + ", notes=" + notes + ", taxRate="
                + taxRate + ", taxStatus=" + taxStatus + ", StatusId=" + StatusId + "]";
    }

}
/*
 * `id` INT(11) NOT NULL AUTO_INCREMENT,
 * `employee_id` INT(11) NULL DEFAULT NULL,
 * `customer_id` INT(11) NULL DEFAULT NULL,
 * `order_date` DATETIME NULL DEFAULT NULL,
 * `shipped_date` DATETIME NULL DEFAULT NULL,
 * `shipper_id` INT(11) NULL DEFAULT NULL,
 * `ship_name` VARCHAR(50) NULL DEFAULT NULL,
 * `ship_address` LONGTEXT NULL DEFAULT NULL,
 * `ship_city` VARCHAR(50) NULL DEFAULT NULL,
 * `ship_state_province` VARCHAR(50) NULL DEFAULT NULL,
 * `ship_zip_postal_code` VARCHAR(50) NULL DEFAULT NULL,
 * `ship_country_region` VARCHAR(50) NULL DEFAULT NULL,
 * `shipping_fee` DECIMAL(19,4) NULL DEFAULT '0.0000',
 * `taxes` DECIMAL(19,4) NULL DEFAULT '0.0000',
 * `payment_type` VARCHAR(50) NULL DEFAULT NULL,
 * `paid_date` DATETIME NULL DEFAULT NULL,
 * `notes` LONGTEXT NULL DEFAULT NULL,
 * `tax_rate` DOUBLE NULL DEFAULT '0',
 * `tax_status_id` TINYINT(4) NULL DEFAULT NULL,
 * `status_id` TINYINT(4) NULL DEFAULT '0',
 */
