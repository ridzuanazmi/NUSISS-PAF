package nusiss.paf.day23workshop.payload;

public class SearchRequest {
    
    private Integer orderId;

    public SearchRequest() {
    }

    public SearchRequest(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
}
