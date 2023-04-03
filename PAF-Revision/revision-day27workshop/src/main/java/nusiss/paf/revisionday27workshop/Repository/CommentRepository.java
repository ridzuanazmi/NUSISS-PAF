package nusiss.paf.revisionday27workshop.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import nusiss.paf.revisionday27workshop.Model.Comment;
import nusiss.paf.revisionday27workshop.Model.CommentUpdate;
import nusiss.paf.revisionday27workshop.Model.Game;

@Repository
public class CommentRepository {

    @Autowired
    private MongoTemplate mTemplate;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy.HH:mm:ss");

    // Method to insert a new document in the comment collection
    public Comment addComment(String name, int rating,
            String comment, Integer gameId, String c_id) {

        // Find game document/object by "gid"
        Query gameIdQuery = Query.query(Criteria.where("gid").is(gameId));
        Game game = mTemplate.findOne(gameIdQuery, Game.class);

        // Returns a null value if game is null
        if (null == game) {
            return null;
        }

        // Instantiate the current date/time when this line is called
        String now = sdf.format(new Date());

        // Creates the Comment object called review
        Comment review = new Comment();
        review.setUser(name);
        review.setC_id(c_id);
        review.setRating(rating);
        review.setC_text(comment);
        review.setGid(gameId);
        review.setPosted(now);
        review.setGame_name(game.getName());
        // Insert the review in the comment collection in MongoDB
        return mTemplate.insert(review);
    }

    // Method to get game by 'gid'
    public Game getGameById(Integer gameId) {

        // Find game document/object by "gid"
        Query gameIdQuery = Query.query(Criteria.where("gid").is(gameId));
        Game game = mTemplate.findOne(gameIdQuery, Game.class);

        // Returns a null value if game is null
        return game;
    }

    // Method to update comment
    public Comment updateComment(String reviewId, Integer rating, String comment) {

        // Calls the helper method checkCommentById
        Comment currentComment = getCommentById(reviewId);

        // Check if currentComment is null or nah
        if (null == currentComment) {

            return null;

        } else {
            // Instantiate the current date/time when this line is called
            String now = sdf.format(new Date());

            // Sets the CommentUpdate item with values from the controller
            CommentUpdate cUpdate = new CommentUpdate();
            cUpdate.setComment(comment);
            cUpdate.setRating(rating);
            cUpdate.setPosted(now);

            // Adds the CurrentComment object, cUpdate, into the List 'edited'
            currentComment.getEdited().add(cUpdate);

            // Saves the new currentComment with the same "_id" into comment collection
            mTemplate.save(currentComment);

            // Returns the Comment object 'currentComment'
            return currentComment;
        }
    }

    // Method to get comment by "_id"
    public Comment getCommentById(String _id) {

        // Find comment document/object by "_id"
        Query commentIdQuery = Query.query(Criteria.where("_id").is(_id));
        Comment comment = mTemplate.findOne(commentIdQuery, Comment.class);

        // returns a null value if comment is null
        return comment;
    }
}
