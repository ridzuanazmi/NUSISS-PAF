package nusiss.paf.revisionday28workshop.Model;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class GameRatingDTO {
    
    private String rating;
    private List<Document> games = new ArrayList<>();
    private String timestamp;

    public GameRatingDTO() {
    }

    public GameRatingDTO(String rating, List<Document> games, String timestamp) {
        this.rating = rating;
        this.games = games;
        this.timestamp = timestamp;
    }

    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public List<Document> getGames() {
        return games;
    }
    public void setGames(List<Document> games) {
        this.games = games;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    
}
