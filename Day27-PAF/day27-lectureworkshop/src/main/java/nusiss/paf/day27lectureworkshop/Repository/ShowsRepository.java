package nusiss.paf.day27lectureworkshop.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import nusiss.paf.day27lectureworkshop.Model.Shows;

// INCOMPLETE 3/4/2023

@Repository
public class ShowsRepository {

    public static final String C_TVSHOWS = "tvshows";

    @Autowired
    private MongoTemplate mongoTemplate;

    // My own method to display the distinct show name
    public List<Shows> findAllTvShow() {

        // Define a projection to select only the 'name' and 'id' fields
        Query query = new Query();
        query.fields()
                .include("id", "name", "language", "genres", "status");

        List<Shows> showsList = mongoTemplate.find(query, Shows.class, "tvshows");

        return showsList;
    }

    // My own method to find/select a show via the name
    public Shows findShowByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, Shows.class);
    }

    // My own method to update a show via the name
    public void updateShow(Shows updatedShow) {
        // Create a query to find the document by its 'name'
        Query query = new Query(Criteria.where("_id").is(updatedShow.getId()));
    
        // Create an Update object to set the new values for the attributes
        Update update = new Update()
                .set("name", updatedShow.getName())
                .set("language", updatedShow.getLanguage())
                .set("status", updatedShow.getStatus())
                .set("genres", updatedShow.getGenres());
    
        // Execute the update operation
        mongoTemplate.updateFirst(query, update, Shows.class);
    }
}
