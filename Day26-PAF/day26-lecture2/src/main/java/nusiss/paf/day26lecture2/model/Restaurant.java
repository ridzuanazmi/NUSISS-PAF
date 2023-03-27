package nusiss.paf.day26lecture2.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "restaurant")
public class Restaurant {
    
    @Field("_id")
    private String id;

    @Field("URL")
    private String url;
    private String address;

    @Field("address line 2")
    private String address2;
    private String name;
    private String outcode;
    private int rating;

    @Field("type_of_food")
    private String typeOfFood;

    public Restaurant() {
    }

    public Restaurant(String id, String url, String address, String address2, String name, String outcode, int rating,
            String typeOfFood) {
        this.id = id;
        this.url = url;
        this.address = address;
        this.address2 = address2;
        this.name = name;
        this.outcode = outcode;
        this.rating = rating;
        this.typeOfFood = typeOfFood;
    }

    // Getters/Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOutcode() {
        return outcode;
    }
    public void setOutcode(String outcode) {
        this.outcode = outcode;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getTypeOfFood() {
        return typeOfFood;
    }
    public void setTypeOfFood(String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }

    
}
