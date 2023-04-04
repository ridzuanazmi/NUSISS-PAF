package nusiss.paf.revisionday28workshop.Model;

import java.util.ArrayList;
import java.util.List;

// Data Transfer Object
public class GameReviewDTO {
    
    private String gameId;
    private String name;
    private int year;
    private int rank;
    private double average;
    private int usersRated;
    private String url;
    private String thumbnail;
    private List<String> reviews = new ArrayList<>();
    private String timestamp;

    public GameReviewDTO() {
    }
    
    public GameReviewDTO(String gameId, String name, int year, int rank, double average, int usersRated, String url,
            String thumbnail, List<String> reviews, String timestamp) {
        this.gameId = gameId;
        this.name = name;
        this.year = year;
        this.rank = rank;
        this.average = average;
        this.usersRated = usersRated;
        this.url = url;
        this.thumbnail = thumbnail;
        this.reviews = reviews;
        this.timestamp = timestamp;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getUsersRated() {
        return usersRated;
    }

    public void setUsersRated(int usersRated) {
        this.usersRated = usersRated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "GameReviewDTO [gameId=" + gameId + ", name=" + name + ", year=" + year + ", rank=" + rank + ", average="
                + average + ", usersRated=" + usersRated + ", url=" + url + ", thumbnail=" + thumbnail + ", reviews="
                + reviews + ", timestamp=" + timestamp + "]";
    }

    
}
