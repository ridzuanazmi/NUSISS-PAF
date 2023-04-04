package nusiss.paf.revisionday28workshop.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comment")
public class Comment {
    
    @Id
    private String _id;
    private String c_id;
    private String user;
    private int rating;
    private String posted;
    private String c_text;
    private int gid;
    private String game_name;
    private List<CommentUpdate> edited = new ArrayList<>();

    public Comment() {
    }

    public Comment(String _id, String c_id, String user, int rating, String posted, String c_text, int gid,
            String game_name) {
        this._id = _id;
        this.c_id = c_id;
        this.user = user;
        this.rating = rating;
        this.posted = posted;
        this.c_text = c_text;
        this.gid = gid;
        this.game_name = game_name;
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getC_id() {
        return c_id;
    }
    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getPosted() {
        return posted;
    }
    public void setPosted(String posted) {
        this.posted = posted;
    }
    public String getC_text() {
        return c_text;
    }
    public void setC_text(String c_text) {
        this.c_text = c_text;
    }
    public int getGid() {
        return gid;
    }
    public void setGid(int gid) {
        this.gid = gid;
    }
    public String getGame_name() {
        return game_name;
    }
    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }
    public List<CommentUpdate> getEdited() {
        return edited;
    }
    public void setEdited(List<CommentUpdate> edited) {
        this.edited = edited;
    }

    @Override
    public String toString() {
        return "Comment [_id=" + _id + ", c_id=" + c_id + ", user=" + user + ", rating=" + rating + ", posted=" + posted
                + ", c_text=" + c_text + ", gid=" + gid + ", game_name=" + game_name + ", edited=" + edited + "]";
    }
     
}
