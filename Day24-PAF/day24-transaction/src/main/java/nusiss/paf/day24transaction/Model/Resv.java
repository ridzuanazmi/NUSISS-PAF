package nusiss.paf.day24transaction.Model;

import java.sql.Date;

public class Resv {
    
    private Integer id;
    private Date resvDate;
    private String fullName;

    public Resv() {
    }

    public Resv(Integer id, Date resvDate, String fullName) {
        this.id = id;
        this.resvDate = resvDate;
        this.fullName = fullName;
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getResvDate() {
        return resvDate;
    }
    public void setResvDate(Date resvDate) {
        this.resvDate = resvDate;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
}
