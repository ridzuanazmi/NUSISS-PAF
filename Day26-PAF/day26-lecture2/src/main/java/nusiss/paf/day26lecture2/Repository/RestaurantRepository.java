package nusiss.paf.day26lecture2.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import nusiss.paf.day26lecture2.model.Restaurant;

@Repository
public class RestaurantRepository {

    public static final String C_RESTAURANT = "restaurant";

    @Autowired
    private MongoTemplate mongoTemplate;

    // write the native query (MongoDB)
    // db.restaurant.distinct("type_of_food").sort()

    public List<String> findTypeOfFood() {

        return mongoTemplate.query(Restaurant.class)
                .distinct("typeOfFood")
                .as(String.class)
                .all();
    }

    public List<Restaurant> findRestaurantsByTypeOfFood(String typeOfFood) {
        Query query = new Query();
        query.addCriteria(Criteria.where("type_of_food").is(typeOfFood));
        return mongoTemplate.find(query, Restaurant.class);
    }
}
