package nusiss.paf.revisionday26workshop.Payload;

public class SearchRequest {
    
    private Integer limit;
    private Integer offset;

    public SearchRequest(Integer limit, Integer offset) {
        this.limit = limit;
        this.offset = offset;
    }
    
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    
}
