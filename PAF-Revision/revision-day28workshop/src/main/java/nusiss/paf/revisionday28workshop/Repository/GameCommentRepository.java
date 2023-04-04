package nusiss.paf.revisionday28workshop.Repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.mongodb.DBObject;

import nusiss.paf.revisionday28workshop.Model.Comment;
import nusiss.paf.revisionday28workshop.Model.Game;

@Repository
public class GameCommentRepository {

    @Autowired
    private MongoTemplate mTemplate;

    RestTemplate restTemplate = new RestTemplate();

    private static final String GET_COMMENT_ENDPOINT_URL = "http://localhost:8080/review.{review_id}";

    // Method to get game by 'gid'
    public Game getGameById(Integer gid) {

        // Find game document/object by "gid"
        Query gameIdQuery = Query.query(Criteria.where("gid").is(gid));
        Game game = mTemplate.findOne(gameIdQuery, Game.class);

        // Returns a null value if game is null
        return game;
    }

    // Method to get comment by "gid"
    public List<Comment> getCommentByGid(Integer gid) {

        // Find comment documents by "gid"
        Query commentGidQuery = Query.query(Criteria.where("gid").is(gid));
        List<Comment> commentList = mTemplate.find(commentGidQuery, Comment.class);

        // returns a null value if empty
        return commentList;
    }

    // Method to get comment by "_id"
    public Comment getCommentById(String _id) {

        // Find comment document/object by "_id"
        Query commentIdQuery = Query.query(Criteria.where("_id").is(_id));
        Comment comment = mTemplate.findOne(commentIdQuery, Comment.class);

        // returns a null value if comment is null
        return comment;
    }

    // Method to return a list of comments by calling an endpoint URL
    public List<Comment> getCommentFromUrl(String _id) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GET_COMMENT_ENDPOINT_URL)
                .queryParam("gid", _id); // Replace 'gid' with the actual parameter name expected by the API

        String finalUrl = builder.toUriString();

        ResponseEntity<List<Comment>> commentList = restTemplate.exchange(finalUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Comment>>() {
                });
        return commentList.getBody();
    }

    // Method to get the average rating from the comment collection of field rating
    public Double getAvgRating(Integer gid) {

        // stages
        // 1) $match
        MatchOperation matchGid = Aggregation.match(Criteria.where("gid").is(gid));

        // 2) $group
        GroupOperation groupAvg = Aggregation.group().avg("rating").as("avg_rating");

        // Create pipeline from stages
        Aggregation pipeline = Aggregation.newAggregation(matchGid, groupAvg);

        // Perform query
        AggregationResults<DBObject> results = mTemplate.aggregate(pipeline, "comment", DBObject.class);

        DBObject result = results.getUniqueMappedResult();

        // If the result is not null, return the average rating, otherwise return 0
        if (result != null) {
            Double avgRating = (Double) result.get("avg_rating");
            return avgRating;
        } else {
            return 0.0;
        }
    }

    // Method that gets the Game with the highest rating with aggregation
    public List<Document> getGamesWithHighestLowestRatings(String hl) {
        // 1) $limit
        LimitOperation limitTen = Aggregation.limit(10);

        // 2) $lookup
        LookupOperation lookupComments = LookupOperation.newLookup()
                .from("comment")
                .localField("gid")
                .foreignField("gid")
                .as("comments");

        // 3) $unwind
        UnwindOperation unwindComments = Aggregation.unwind("comments");

        // Comparison requires highest or lowest
        if (hl.toLowerCase().equals("highest")) {

            // 4) $sort
            SortOperation sortByCommentsRatingDesc = Aggregation.sort(Direction.DESC, "comments.rating");

            // 5) $group
            GroupOperation groupFields = Aggregation.group("gid")
                    .first("name").as("name")
                    .max("comments.rating").as("max_rating")
                    .first("comments.user").as("user")
                    .first("comments.c_text").as("comment")
                    .first("comments.c_id").as("review_id");

            // 6) $sort
            SortOperation sortByMaxRatingDesc = Aggregation.sort(Direction.DESC, "max_rating");

            // Create aggregation pipeline from stages
            Aggregation pipelineHighest = Aggregation.newAggregation(
                    limitTen,
                    lookupComments,
                    unwindComments,
                    sortByCommentsRatingDesc,
                    groupFields,
                    sortByMaxRatingDesc);

            // Perform the aggregation query
            AggregationResults<Document> resultHighest = mTemplate.aggregate(pipelineHighest, "game", Document.class);

            // Get the result list
            return resultHighest.getMappedResults();

        } else if (hl.toLowerCase().equals("lowest")) {

            // 4) $sort
            SortOperation sortByCommentsRatingAsc = Aggregation.sort(Direction.ASC, "comments.rating");

            // 5) $group
            GroupOperation groupFields = Aggregation.group("gid")
                    .first("name").as("name")
                    .min("comments.rating").as("min_rating")
                    .first("comments.user").as("user")
                    .first("comments.c_text").as("comment")
                    .first("comments.c_id").as("review_id");

            // 6) $sort
            SortOperation sortByMinRatingAsc = Aggregation.sort(Direction.ASC, "min_rating");

            // Create aggregation pipeline from stages
            Aggregation pipelineLowest = Aggregation.newAggregation(
                    limitTen,
                    lookupComments,
                    unwindComments,
                    sortByCommentsRatingAsc,
                    groupFields,
                    sortByMinRatingAsc);

            // Perform the aggregation query
            AggregationResults<Document> resultLowest = mTemplate.aggregate(pipelineLowest, "game", Document.class);

            // Get the result list
            return resultLowest.getMappedResults();

        } else {

            return null;

        }

    }
}
