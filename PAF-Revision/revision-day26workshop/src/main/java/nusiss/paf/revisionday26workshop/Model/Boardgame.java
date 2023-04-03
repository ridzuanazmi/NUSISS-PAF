package nusiss.paf.revisionday26workshop.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "game")
public class Boardgame {
    
    @Id
    private String id;
    private String gid;
    private String name;
    private int year;
    private int ranking;
    private int users_rated;
    private String url;
    private String image;
    
    public Boardgame() {
    }
    public Boardgame(String gid, String name) {
        this.gid = gid;
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getGid() {
        return gid;
    }
    public void setGid(String gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getRanking() {
        return ranking;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public int getUsers_rated() {
        return users_rated;
    }
    public void setUsers_rated(int users_rated) {
        this.users_rated = users_rated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public String toString() {
        return "Boardgame [gid=" + gid + ", name=" + name + "]";
    }
}
