package nusiss.paf.day26lecture2.model;

import java.io.StringReader;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

// indicates that the Restaurant class should be mapped to a MongoDB collection.
// The collection attribute specifies the name of the MongoDB collection to map
// this class to, which is "restaurants" in this case.
@Document(collection = "restaurant")
public class Restaurant {

    // The @Field annotation is used to map the typeOfFood field in the Restaurant
    // class to the corresponding field in the MongoDB document. The annotation's
    // value, "type_of_food", indicates the name of the field in the MongoDB
    // document that this class field should be mapped to.
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

    @Override
    public String toString() {
        return "Restaurant [id=" + id + ", url=" + url + ", address=" + address + ", address2=" + address2 + ", name="
                + name + ", outcode=" + outcode + ", rating=" + rating + ", typeOfFood=" + typeOfFood + "]";
    }

    public static Restaurant toRestaurant(String jsonString) {

        Restaurant restaurant = new Restaurant();
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();

        restaurant.setName(jsonObject.getString("name"));
        restaurant.setAddress(jsonObject.getString("address"));
        restaurant.setTypeOfFood(jsonObject.getString("type_of_food"));
        return restaurant;
    }
}
