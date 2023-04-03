package nusiss.paf.revisionday27workshop.Model;

public class CommentUpdate {
    
    private String comment;
    private int rating;
    private String posted;

    public CommentUpdate() {
    }

    public CommentUpdate(String comment, int rating, String posted) {
        this.comment = comment;
        this.rating = rating;
        this.posted = posted;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
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

    @Override
    public String toString() {
        return "CommentUpdate [comment=" + comment + ", rating=" + rating + ", posted=" + posted + "]";
    }
    
}
