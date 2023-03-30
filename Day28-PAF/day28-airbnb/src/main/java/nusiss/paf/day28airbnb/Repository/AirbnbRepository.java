package nusiss.paf.day28airbnb.Repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;

import nusiss.paf.day28airbnb.Model.Airbnb;

@Repository
public class AirbnbRepository {
    
    private static final String C_AIRBNB = "airbnb";

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Airbnb> findAirbnbByCountry(String seacrhText) {

        // Stages
        MatchOperation matchOperation = Aggregation.match(
                TextCriteria.forDefaultLanguage().matching(seacrhText)); // TextCriteria have to be the first in the pipeline

        ProjectionOperation selectFields = Aggregation.project(
                "name", "summary", "property_type", "room_type", "address")
                .andExclude("_id");

        // Create pipeline from stages
        Aggregation pipeline = Aggregation.newAggregation(matchOperation, selectFields);

        // Perform query
        AggregationResults<Airbnb> results = mongoTemplate.aggregate(pipeline, C_AIRBNB, Airbnb.class);

        printIt(results);
        return results.getMappedResults();
    }

    public void printIt(AggregationResults<Airbnb> results) {

        for (Airbnb d : results) {
            System.out.printf(">>> %s\n",d.toString());
        }
    }
}
