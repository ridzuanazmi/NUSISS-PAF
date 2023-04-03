package nusiss.paf.revisionday26workshop.Repository;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import nusiss.paf.revisionday26workshop.Model.Boardgame;
import nusiss.paf.revisionday26workshop.Model.GameAggregation;

@Repository
public class BoardgamesRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    // Method that returns JsonObject as required by workshop
    // (Using Boardgame object)
    public JsonObject getGames(Integer limit, Integer offset) {

        // Get the documents that contains "gid" and set the limit and offset
        Query query = Query
                .query(Criteria.where("gid").exists(true))
                .limit(limit).skip(offset);

        // Get the total count of document in the game class with the queries set
        int totalCount = (int) mongoTemplate.count(query, Boardgame.class, "game");

        // With the queries set, store it as a list of Document class
        List<Boardgame> results = mongoTemplate.find(query, Boardgame.class, "game");

        JsonObject gamesJsonObject = toJson(results, limit, offset, totalCount);
        return gamesJsonObject;
    }

    // Method that returns JsonObject that ranks the game 
    // in ascending order
    public JsonObject getGamesRank(int limit, int offset) {

        // Get the documents that contains "ranking" and set the limit and offset
        Query query = Query
                .query(Criteria.where("ranking").exists(true))
                .with(Sort.by(Sort.Direction.ASC, "ranking"))
                .limit(limit).skip(offset);

        // Get the total count of document in the game class with the queries set
        int totalCount = (int) mongoTemplate.count(query, Boardgame.class, "game");

        // With the queries set, store it as a list of Document class
        List<Boardgame> results = mongoTemplate.find(query, Boardgame.class, "game");

        JsonObject gamesJsonObject = toJsonRank(results, limit, offset, totalCount);

        return gamesJsonObject;
    }

    // Method that returns JsonObject as required by workshop
    // (Using Boardgame object)
    public JsonObject getGameById(Integer gameId) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy.HH:mm:ss");
        String now = sdf.format(new Date());

        // Stages
        // 1) $match
        MatchOperation matchOperation = Aggregation.match(Criteria.where("gid").is(gameId));

        // 2) $lookup
        LookupOperation lookupOperation = Aggregation.lookup("comment", "gid", "gid", "comments");

        // 3) $unwind "comments" but it is not in aggregation, will do so later in
        // pipeline
        // 4) $group
        GroupOperation groupOperation = Aggregation
                .group("gid")
                .first("gid").as("gid")
                .first("name").as("name")
                .first("year").as("year")
                .first("ranking").as("ranking")
                .avg("comments.rating").as("average_rating")
                .count().as("users_rated")
                .first("url").as("url")
                .first("image").as("thumbnail");

        // 5) $project
        ProjectionOperation projectOperation = Aggregation
                .project("gid", "name", "year", "ranking", "average_rating", "users_rated", "url", "thumbnail")
                .andExpression("'%s'".formatted(now)).as("timestamp");

        // Create pipeline from stages
        Aggregation pipeline = Aggregation.newAggregation(
                matchOperation, lookupOperation, Aggregation.unwind("comments"), groupOperation, projectOperation);

        // Perform Query
        AggregationResults<GameAggregation> results = mongoTemplate.aggregate(pipeline, "game", GameAggregation.class);

        // Return the first element of the list, or null if the list is empty
        List<GameAggregation> mappedResults = results.getMappedResults();

        if (mappedResults.isEmpty()) {
                return null;
            }

        GameAggregation gameAggregation = mappedResults.get(0);
        return gameAggregation.toJson(); // calls for the GameAggregation toJson method
    }

    // Method that returns a List of Documents in Bson
    // that matches the query in this method
    public List<Document> getGames2(Integer limit, Integer offset) {

        Query query = Query
                .query(Criteria.where("gid").exists(true))
                .limit(limit).skip(offset);
        query.fields()
                .exclude("_id")
                .include("gid", "name");

        List<Document> results = mongoTemplate.find(query, Document.class, "game");

        return results;
    }

    // Method to convert List<Boardgame> object into JsonObject
    public static JsonObject toJson(List<Boardgame> games, Integer limit, Integer offset, int totalCount) {

        // Create a JsonArrayBuilder to store the board games
        JsonArrayBuilder gamesJsonArrayBuilder = Json.createArrayBuilder();

        // Iterate through the results and add each Boardgame object as a JsonObject to
        // the JsonArrayBuilder
        for (Boardgame game : games) {
            JsonObjectBuilder gameJsonBuilder = Json.createObjectBuilder();
            gameJsonBuilder.add("game_id", game.getGid());
            gameJsonBuilder.add("name", game.getName());
            gamesJsonArrayBuilder.add(gameJsonBuilder);
        }
        // Build the JsonArray from the JsonArrayBuilder
        JsonArray gamesArray = gamesJsonArrayBuilder.build();

        // Create a JsonObjectBuilder to build the final JsonObject
        JsonObjectBuilder resultBuilder = Json.createObjectBuilder()
                .add("games", gamesArray)
                .add("offset", offset)
                .add("limit", limit)
                .add("total", totalCount)
                .add("timestamp", LocalDateTime.now().toString());

        // Build and return the JsonObject
        JsonObject gamesJsonObject = resultBuilder.build();
        return gamesJsonObject;
    }

    // Method to convert List<Boardgame> object into JsonObject. includes rank in
    // JsonObject
    private static JsonObject toJsonRank(List<Boardgame> games, Integer limit, Integer offset, int totalCount) {

        // Create a JsonArrayBuilder to store the board games
        JsonArrayBuilder gamesJsonArrayBuilder = Json.createArrayBuilder();

        // Iterate through the results and add each Boardgame object as a JsonObject to
        // the JsonArrayBuilder
        for (Boardgame game : games) {
            JsonObjectBuilder gameJsonBuilder = Json.createObjectBuilder()
                    .add("game_id", game.getGid())
                    .add("name", game.getName())
                    .add("rank", game.getRanking());
            gamesJsonArrayBuilder.add(gameJsonBuilder);
        }
        // Build the JsonArray from the JsonArrayBuilder
        JsonArray gamesArray = gamesJsonArrayBuilder.build();

        // Create a JsonObjectBuilder to build the final JsonObject
        JsonObjectBuilder resultBuilder = Json.createObjectBuilder()
                .add("games", gamesArray)
                .add("offset", offset)
                .add("limit", limit)
                .add("total", totalCount)
                .add("timestamp", LocalDateTime.now().toString());

        // Build and return the JsonObject
        JsonObject gamesJsonObject = resultBuilder.build();
        return gamesJsonObject;
    }

}
