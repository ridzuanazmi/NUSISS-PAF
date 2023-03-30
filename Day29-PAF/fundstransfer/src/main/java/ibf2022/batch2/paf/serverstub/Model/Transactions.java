package ibf2022.batch2.paf.serverstub.Model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
public class Transactions {
    
    @Id
    private UUID transactionId;
    private String from;
    private String to;
    private Float transferAmount;

    public Transactions() {
    }

    public Transactions(UUID transactionId, String from, String to, Float transferAmount) {
        this.transactionId = transactionId;
        this.from = from;
        this.to = to;
        this.transferAmount = transferAmount;
    }
    
    public UUID getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public Float getTransferAmount() {
        return transferAmount;
    }
    public void setTransferAmount(Float transferAmount) {
        this.transferAmount = transferAmount;
    }

    
}
