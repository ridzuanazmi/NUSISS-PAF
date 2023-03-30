package nusiss.paf.day29revisiondate.Model;

public class Item {
    
    private String name;
    private int amount;
    private Float price;
    
    public Item() {
    }

    public Item(String name, int amount, Float price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
}
