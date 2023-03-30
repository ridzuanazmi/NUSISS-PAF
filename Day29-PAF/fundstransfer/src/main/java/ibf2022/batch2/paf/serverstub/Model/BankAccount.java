package ibf2022.batch2.paf.serverstub.Model;

public class BankAccount {
    
    private int id;
    private String customerName;
    private Float balance;

    public BankAccount() {
    }

    public BankAccount(int id, String customerName, Float balance) {
        this.id = id;
        this.customerName = customerName;
        this.balance = balance;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Float getBalance() {
        return balance;
    }
    public void setBalance(Float balance) {
        this.balance = balance;
    }

    
}
