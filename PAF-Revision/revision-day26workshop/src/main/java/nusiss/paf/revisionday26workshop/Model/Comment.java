package nusiss.paf.revisionday26workshop.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comment")
public class Comment {
    
    private int gid;
    private int rating;

    public Comment() {
    }

    public Comment(int gid, int rating) {
        this.gid = gid;
        this.rating = rating;
    }
    
    public int getGid() {
        return gid;
    }
    public void setGid(int gid) {
        this.gid = gid;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    
}
