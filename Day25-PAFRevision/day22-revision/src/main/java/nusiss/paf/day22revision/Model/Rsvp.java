package nusiss.paf.day22revision.Model;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public class Rsvp {
    
    private int id;
    
    @NotNull(message = "Required field")
    private String fullName;

    @NotNull(message = "Required field")
    private String email;

    @NotNull(message = "Required field")
    private String phone;

    @NotNull(message = "Required field")
    private Date confirmationDate;

    @NotNull(message = "Required field")
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

    @Override
    public String toString() {
        return "Rsvp [id=" + id + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone
                + ", confirmationDate=" + confirmationDate + ", comments=" + comments + "]";
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