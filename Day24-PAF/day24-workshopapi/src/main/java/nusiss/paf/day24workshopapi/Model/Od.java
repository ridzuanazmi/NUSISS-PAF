package nusiss.paf.day24workshopapi.Model;

public class Od {
    private Order order;
    private OrderDetail orderDetail;

    public Od() {
    }

    public Od(Order order, OrderDetail orderDetail) {
        this.order = order;
        this.orderDetail = orderDetail;
    }

    // Getters/Setters
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public OrderDetail getOrderDetail() {
        return orderDetail;
    }
    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
    
}
