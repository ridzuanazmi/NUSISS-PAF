package nusiss.paf.revisionday26workshop.Model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class GameAggregation {
    
    private int gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Double average_rating;
    private Integer users_rated;
    private String url;
    private String thumbnail;
    private String timestamp;

    public GameAggregation() {
    }

    public GameAggregation(int gid, String name, Integer year, Integer ranking, Double average_rating,
            Integer users_rated, String url, String thumbnail, String timestamp) {
        this.gid = gid;
        this.name = name;
        this.year = year;
        this.ranking = ranking;
        this.average_rating = average_rating;
        this.users_rated = users_rated;
        this.url = url;
        this.thumbnail = thumbnail;
        this.timestamp = timestamp;
    }

    public int getGid() {
        return gid;
    }
    public void setGid(int gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getRanking() {
        return ranking;
    }
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    public Double getAverage_rating() {
        return average_rating;
    }
    public void setAverage_rating(Double average_rating) {
        this.average_rating = average_rating;
    }
    public Integer getUsers_rated() {
        return users_rated;
    }
    public void setUsers_rated(Integer users_rated) {
        this.users_rated = users_rated;
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
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "GameAggregation [gid=" + gid + ", name=" + name + ", year=" + year + ", ranking="
                + ranking + ", average_rating=" + average_rating + ", users_rated=" + users_rated + ", url=" + url
                + ", thumbnail=" + thumbnail + ", timestamp=" + timestamp + "]";
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("game_id", gid)
                .add("name", name)
                .add("year", year)
                .add("ranking", ranking)
                .add("average_rating", average_rating)
                .add("users_rated", users_rated)
                .add("url", url)
                .add("thumbnail", thumbnail)
                .add("timestamp", timestamp)
                .build();
    }
}
