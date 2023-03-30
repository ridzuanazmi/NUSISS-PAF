package nusiss.paf.day27lecture.Repository;

import java.sql.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import jakarta.json.JsonObject;

@Repository
public class TaskRepository {

    public static final String C_ACTIVITIES = "activities";
    @Autowired
    private MongoTemplate mongoTemplate;

    /*
     * /*
     * db.activities.insertOne({
     * task: 'abc',
     * priority: 1,
     * dueDate: ISODate('YYYY-mm-dd')
     * })
     */
    public Document insertTask(JsonObject task) {

        Document toInsert = Document.parse(task.toString());
        toInsert.put("dueDate", Date.valueOf(toInsert.getString("dueDate")));

        return mongoTemplate.insert(toInsert, C_ACTIVITIES);
    }

    /*
     * db.activities.find({
     *  task: {
     *      $exists: false
     *  }
     * })
     */
    public void findWithoutTask() {

        Criteria criteria = Criteria.where("task")
                .exists(false);

        Query query = Query.query(criteria);

        List<Document> results = mongoTemplate.find(query, Document.class, C_ACTIVITIES);
        System.out.printf(">>> ", results);
    }

    /*
     * db.activities.deleteMany(
     * {task : { $exists: false } }
     * )
     */
    public void deleteActivitiesWithoutTask() {

        Criteria criteria = Criteria.where("task")
                .exists(false);

        Query query = Query.query(criteria);

        // remove() is to remove the document from the collection
        DeleteResult deleted = mongoTemplate.remove(query, C_ACTIVITIES);
        // To show how many is deleted and did it happen. Something extra
        System.out.printf(">>> delete count: %d\n", deleted.getDeletedCount());
        System.out.printf(">>> ack : %b\n", deleted.wasAcknowledged());
    }

    /*
     * db.activities.updateMany(
     * { priority: { $gte: 7 } },
     * { $set: { important: true } }
     * )
     */
    public void updateActivity() {
        Criteria criteria = Criteria.where("priority")
                .gte(7);

        Query query = Query.query(criteria);

        Update updateOps = new Update()
                .set("important", true);

        UpdateResult result = mongoTemplate.updateMulti(query, updateOps, Document.class, C_ACTIVITIES);

        System.out.printf("matched: %d\n", result.getMatchedCount());
        System.out.printf("modified: %d\n", result.getModifiedCount());
        System.out.printf("ack: %b\n", result.wasAcknowledged());
    }
}
