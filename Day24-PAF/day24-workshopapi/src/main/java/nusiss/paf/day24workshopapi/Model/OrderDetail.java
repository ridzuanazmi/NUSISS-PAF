package nusiss.paf.day24workshopapi.Model;

public class OrderDetail {
    
    private int id;
    private int orderId;
    private String product;
    private Float unitPrice;
    private Float discount;
    private int quantity;
    
    // Constructors
    public OrderDetail() {
    }

    public OrderDetail(int id, int orderId, String product, Float unitPrice, Float discount, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.product = product;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.quantity = quantity;
    }

    // Getters/Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public Float getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Float getDiscount() {
        return discount;
    }
    public void setDiscount(Float discount) {
        this.discount = discount;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void add(int quantity) {
		this.quantity += quantity;
	}
	public void add() {
		this.quantity++;
	}

    @Override
    public String toString() {
        return "OrderDetail [id=" + id + ", orderId=" + orderId + ", product=" + product + ", unitPrice=" + unitPrice
                + ", discount=" + discount + ", quantity=" + quantity + "]";
    }
    
}
