package nusiss.paf.day28lecture.Repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class TvShowRepository {

    private static final String C_TVSHOWS = "tvshows";

    @Autowired
    private MongoTemplate mongoTemplate;
    
    public void findTvShowByType(String type) {

        // Stages
        MatchOperation matchOperation = Aggregation.match(
                Criteria.where("type").regex(type, "i"));

        ProjectionOperation selectFields = Aggregation.project("id", "name", "summary")
                .andExclude("_id");

        // Create the pipelne from stages
        Aggregation pipeline = Aggregation.newAggregation(matchOperation, selectFields);

        // Perform the query
        AggregationResults<Document> results = mongoTemplate.aggregate(
                pipeline, C_TVSHOWS, Document.class);

        printIt(results);
    }

    /* 
     * db.tvshows.aggregate([
     *  
     * ])
     */
    public void groupShowsByTimezone() {

        // Stage
        GroupOperation tzGroup = Aggregation.group("network.country.timezone")
                .count().as("total_shows")
                .push("name").as("titles");

        // Create pipeline
        Aggregation pipeline = Aggregation.newAggregation(tzGroup);

        // Perform the query
        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, C_TVSHOWS, Document.class);

        printIt(results);
    }

    public void summarizeTvSHowsByType(String type) {

        // Stage
        MatchOperation filterByType = Aggregation.match(
            Criteria.where("type").regex(type, "i"));

        ProjectionOperation summarizeFields = Aggregation.project("id", "type")
                .and(
                    AggregationExpression.from(
                        MongoExpression.create("""
                            $concat: [ "$name", " (", { $toString: "$runtime" }, "mins)"]
                        """)
                    )
                ).as("title")
                .andExclude("_id");

        SortOperation orderByTitle = Aggregation.sort(
                Sort.by(Direction.ASC, "title"));
                
        // Create pipeline
        Aggregation pipeline = Aggregation.newAggregation(filterByType, summarizeFields, orderByTitle);

        // Perform the query
        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, C_TVSHOWS, Document.class);

        printIt(results);
    } 

    public void showCategories() {

        // Stages
        UnwindOperation unwindGenres = Aggregation.unwind("genres");

        GroupOperation groupGenres = Aggregation.group("abc")
                .addToSet(unwindGenres) 
    }

    public void printIt(AggregationResults<Document> results) {

        for (Document document : results) {
            System.out.printf(">>> %s\n", document.toJson());
        }
    }

}
