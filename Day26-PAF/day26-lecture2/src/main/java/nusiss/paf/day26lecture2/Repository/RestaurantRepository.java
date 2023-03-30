package nusiss.paf.day26lecture2.Repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

    // This is my own method without using chuk's query String that he taught
    public List<String> findTypeOfFood() {

        return mongoTemplate.query(Restaurant.class)
                .distinct("typeOfFood")
                .as(String.class)
                .all();
    }

    // This is my own method without using chuk's query String that he taught
    public List<Restaurant> findRestaurantsByTypeOfFood(String typeOfFood) {
        Query query = new Query();
        query.addCriteria(Criteria.where("type_of_food").is(typeOfFood));
        return mongoTemplate.find(query, Restaurant.class);
    }

    //---------------------------- CHUK'S METHOD ---------------------------------\\
    // db.restaurant.find({
	// type_of_food: {
	// $regex: 'cuisine', $options: "i"
	// }
	// })
	// .sort({ name: -1 })
	// .projection({ name: 1, address: 1, type_of_food: 1, _id: 0 })
	public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
		Criteria criteria = Criteria.where("type_of_food").regex(cuisine, "i");

		Query query = Query.query(criteria)
				.with(Sort.by(Direction.ASC, "name"));
		query.fields()
				.include("name", "address", "type_of_food", "rating")
				.exclude("_id");

		return mongoTemplate.find(query, Document.class, C_RESTAURANT)
				.stream()
				.map(doc -> doc.toJson())
				.map(Restaurant::toRestaurant)
				.toList();
	}
}
