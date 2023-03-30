package ibf2022.batch2.paf.serverstub.Payload;

public class TransferRequest {
    
    private String srcAcct;
    private String destAcct;
    private Float amount;

    public TransferRequest() {
    }

    public TransferRequest(String srcAcct, String destAcct, Float amount) {
        this.srcAcct = srcAcct;
        this.destAcct = destAcct;
        this.amount = amount;
    }
    
    public String getSrcAcct() {
        return srcAcct;
    }
    public void setSrcAcct(String srcAcct) {
        this.srcAcct = srcAcct;
    }
    public String getDestAcct() {
        return destAcct;
    }
    public void setDestAcct(String destAcct) {
        this.destAcct = destAcct;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransferRequest [srcAcct=" + srcAcct + ", destAcct=" + destAcct + ", amount=" + amount + "]";
    }

    
}
