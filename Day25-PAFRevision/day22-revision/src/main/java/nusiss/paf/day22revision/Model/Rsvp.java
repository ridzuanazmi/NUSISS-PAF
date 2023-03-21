package nusiss.paf.day22revision.Model;

import java.sql.Date;

public class Rsvp {
    
    private int id;
    private String fullName;
    private String email;
    private String phone;
    private Date confirmationDate;
    private String comments;

    public Rsvp() {
    }

    public Rsvp(int id, String fullName, String email, String phone, Date confirmationDate, String comments) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.confirmationDate = confirmationDate;
        this.comments = comments;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Date getConfirmationDate() {
        return confirmationDate;
    }
    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }


}

/* 
    id					int not null auto_increment,
    full_name			varchar(100) not null,
    email				varchar(100) not null,
    phone				varchar(20) not null,
    confirmation_date	date,
    comments			text,
 */