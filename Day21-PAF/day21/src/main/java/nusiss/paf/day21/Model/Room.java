package nusiss.paf.day21.Model;

public class Room {
    
    private int id;
    private float price;
    private String roomType;

    // Empty Constructor
    public Room() {
    }

    // Full constructor
    public Room(int id, float price, String roomType) {
        this.id = id;
        this.price = price;
        this.roomType = roomType;
    }

    // Getters/Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    
}
