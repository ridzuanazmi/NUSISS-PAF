package nusiss.paf.day21workshop.Model;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

public class Customer {
    
    // fields
    private Integer id;
    private String company;
    private String lastName;
    private String firstName;
    private String email;
    private String jobTitle;
    private String businessPhone;
    private String homePhone;
    private String mobilePhone;
    private String faxNumber;
    private String address;
    private String city;
    private String stateProvince;
    private String zipPostalCode;
    private String country;
    private String webPage;
    private String notes;

    // Empty constructor
    public Customer() {
    }
    
    // All constructor
    public Customer(Integer id, String company, String lastName, String firstName, String email, String jobTitle,
            String businessPhone, String homePhone, String mobilePhone, String faxNumber, String address, String city,
            String stateProvince, String zipPostalCode, String country, String webPage, String notes) {
        this.id = id;
        this.company = company;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.jobTitle = jobTitle;
        this.businessPhone = businessPhone;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.faxNumber = faxNumber;
        this.address = address;
        this.city = city;
        this.stateProvince = stateProvince;
        this.zipPostalCode = zipPostalCode;
        this.country = country;
        this.webPage = webPage;
        this.notes = notes;
    }

    // getters/setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getBusinessPhone() {
        return businessPhone;
    }
    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public String getFaxNumber() {
        return faxNumber;
    }
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStateProvince() {
        return stateProvince;
    }
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }
    public String getZipPostalCode() {
        return zipPostalCode;
    }
    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getWebPage() {
        return webPage;
    }
    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", company=" + company + ", lastName=" + lastName + ", firstName=" + firstName
                + ", email=" + email + ", jobTitle=" + jobTitle + ", businessPhone=" + businessPhone + ", homePhone="
                + homePhone + ", mobilePhone=" + mobilePhone + ", faxNumber=" + faxNumber + ", address=" + address
                + ", city=" + city + ", stateProvince=" + stateProvince + ", zipPostalCode=" + zipPostalCode
                + ", country=" + country + ", webPage=" + webPage + ", notes=" + notes + "]";
    }
    
    public JsonObject toJson() {

        return Json.createObjectBuilder()
            .add("id", getId())
            .add("company", getCompany() != null ? Json.createValue(getCompany()) : JsonValue.NULL)
            .add("last_name", getLastName() != null ? Json.createValue(getLastName()) : JsonValue.NULL)
            .add("first_name", getFirstName() != null ? Json.createValue(getFirstName()) : JsonValue.NULL)
            .add("email_address", getEmail() != null ? Json.createValue(getEmail()) : JsonValue.NULL)
            .add("job_title", getJobTitle() != null ? Json.createValue(getJobTitle()) : JsonValue.NULL)
            .add("business_phone", getBusinessPhone() != null ? Json.createValue(getBusinessPhone()) : JsonValue.NULL) 
            .add("home_phone", getHomePhone() != null ? Json.createValue(getHomePhone()) : JsonValue.NULL)
            .add("mobile_phone", getMobilePhone() != null ? Json.createValue(getMobilePhone()) : JsonValue.NULL)
            .add("fax_number", getFaxNumber() != null ? Json.createValue(getFaxNumber()) : JsonValue.NULL)
            .add("address", getAddress() != null ? Json.createValue(getAddress()) : JsonValue.NULL)
            .add("city", getCity() != null ? Json.createValue(getCity()) : JsonValue.NULL)
            .add("state_province", getStateProvince() != null ? Json.createValue(getStateProvince()) : JsonValue.NULL)
            .add("zip_postal_code", getZipPostalCode() != null ? Json.createValue(getZipPostalCode()) : JsonValue.NULL)
            .add("country_region", getCountry() != null ? Json.createValue(getCountry()) : JsonValue.NULL)
            .add("web_page", getWebPage() != null ? Json.createValue(getWebPage()) : JsonValue.NULL)
            .add("notes", getNotes() != null ? Json.createValue(getNotes()) : JsonValue.NULL)
            .build();
    }
}

//    `id` INT(11) NOT NULL AUTO_INCREMENT,
//   `company` VARCHAR(50) NULL DEFAULT NULL,
//   `last_name` VARCHAR(50) NULL DEFAULT NULL,
//   `first_name` VARCHAR(50) NULL DEFAULT NULL,
//   `email_address` VARCHAR(50) NULL DEFAULT NULL,
//   `job_title` VARCHAR(50) NULL DEFAULT NULL,
//   `business_phone` VARCHAR(25) NULL DEFAULT NULL,
//   `home_phone` VARCHAR(25) NULL DEFAULT NULL,
//   `mobile_phone` VARCHAR(25) NULL DEFAULT NULL,
//   `fax_number` VARCHAR(25) NULL DEFAULT NULL,
//   `address` LONGTEXT NULL DEFAULT NULL,
//   `city` VARCHAR(50) NULL DEFAULT NULL,
//   `state_province` VARCHAR(50) NULL DEFAULT NULL,
//   `zip_postal_code` VARCHAR(15) NULL DEFAULT NULL,
//   `country_region` VARCHAR(50) NULL DEFAULT NULL,
//   `web_page` LONGTEXT NULL DEFAULT NULL,
//   `notes` LONGTEXT NULL DEFAULT NULL,
//   `attachments` LONGBLOB NULL DEFAULT NULL,