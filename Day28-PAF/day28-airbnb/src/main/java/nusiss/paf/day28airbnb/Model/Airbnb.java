package nusiss.paf.day28airbnb.Model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "airbnb")
public class Airbnb {
    
    private String name;
    private String summary;

    @Field("property_type")
    private String propertyType;

    @Field("room_type")
    private String roomType;
    private Address address;

    // constructor
    public Airbnb() {
    }
    
    public Airbnb(String name, String summary, String propertyType, String roomType, Address address) {
        this.name = name;
        this.summary = summary;
        this.propertyType = propertyType;
        this.roomType = roomType;
        this.address = address;
    }

    // Getters/Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getPropertyType() {
        return propertyType;
    }
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public static class Address {
        private String street;
        private String suburb;
        private String government_area;
        private String market;
        private String country;
        private String country_code;
        
        @Field("location")
        private Location location;

        public Address() {
        }

        public Address(String street, String suburb, String government_area, String market, String country,
                String country_code, Location location) {
            this.street = street;
            this.suburb = suburb;
            this.government_area = government_area;
            this.market = market;
            this.country = country;
            this.country_code = country_code;
            this.location = location;
        }

        public String getStreet() {
            return street;
        }
        public void setStreet(String street) {
            this.street = street;
        }
        public String getSuburb() {
            return suburb;
        }
        public void setSuburb(String suburb) {
            this.suburb = suburb;
        }
        public String getGovernment_area() {
            return government_area;
        }
        public void setGovernment_area(String government_area) {
            this.government_area = government_area;
        }
        public String getMarket() {
            return market;
        }
        public void setMarket(String market) {
            this.market = market;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
        public String getCountry_code() {
            return country_code;
        }
        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }
        public Location getLocation() {
            return location;
        }
        public void setLocation(Location location) {
            this.location = location;
        }

        @Override
        public String toString() {
            return "Address [street=" + street + ", suburb=" + suburb + ", government_area=" + government_area
                    + ", market=" + market + ", country=" + country + ", country_code=" + country_code + ", location="
                    + location + "]";
        }
        
    }// End of Address Class

    public static class Location {
        private String type;
        private List<Double> coordinates;
        private boolean is_location_exact;

        // Constructors, getters, and setters
        public Location() {
        }

        public Location(String type, List<Double> coordinates, boolean is_location_exact) {
            this.type = type;
            this.coordinates = coordinates;
            this.is_location_exact = is_location_exact;
        }

        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public List<Double> getCoordinates() {
            return coordinates;
        }
        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }
        public boolean isIs_location_exact() {
            return is_location_exact;
        }
        public void setIs_location_exact(boolean is_location_exact) {
            this.is_location_exact = is_location_exact;
        }

        @Override
        public String toString() {
            return "Location [type=" + type + ", coordinates=" + coordinates + ", is_location_exact="
                    + is_location_exact + "]";
        }
        
    } // End of Location class

    @Override
    public String toString() {
        return "Airbnb [name=" + name + ", summary=" + summary + ", propertyType=" + propertyType + ", roomType="
                + roomType + ", address=" + address + "]";
    }

}
