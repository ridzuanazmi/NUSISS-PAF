package nusiss.paf.revisionday27workshop.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nusiss.paf.revisionday27workshop.Model.Comment;
import nusiss.paf.revisionday27workshop.Model.CommentUpdate;
import nusiss.paf.revisionday27workshop.Model.Game;
import nusiss.paf.revisionday27workshop.Repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository cRepo;

    public Comment addComment(String name, int rating,
            String comment, Integer gameId, String c_id) {

        return cRepo.addComment(name, rating, comment, gameId, c_id);
    }

    public Game getGameById(Integer gameId) {
        return cRepo.getGameById(gameId);
    }

    public Comment updateComment(String reviewId, Integer rating, String comment) {
        return cRepo.updateComment(reviewId, rating, comment);
    }

    public ResponseEntity<String> checkRating(int rating) {
        if (rating < 0 || rating > 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Rating should be 0 - 10 inclusive");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Rating is valid");
    }

    public Comment getCommentById(String _id) {
        return cRepo.getCommentById(_id);
    }

    // Helper method for the controller
    public Map<String, Object> getLatestReviewData(String reviewId) {
        // Find the comment by its reviewId
        Comment comment = getCommentById(reviewId);
        
        // If the comment is not found, return null
        if (comment == null) {
            return null;
        }

        System.out.printf(">>>> %s\n",comment.toString());

        // Get the Game object with the same "gid" as comment
        Game game = getGameById(comment.getGid());

        // Initialize the response map to store the result
        Map<String, Object> response = new HashMap<>();

        // Get the list of edited comments from the Comment object
        List<CommentUpdate> edited = comment.getEdited();

        // Check if there are any edited comments
        boolean isEdited = !edited.isEmpty();

        // Get the latest comment and rating
        // If true, from the edited list. False, the original comment
        String latestComment = isEdited ? edited.get(edited.size() - 1).getComment() : comment.getC_text();
        int latestRating = isEdited ? edited.get(edited.size() - 1).getRating() : comment.getRating();

        // Get the latest posted date, either from the edited list or the original
        // comment
        String posted = isEdited ? edited.get(edited.size() - 1).getPosted() : comment.getPosted();

        // Add the required fields to the response map
        response.put("user", comment.getUser());
        response.put("rating", latestRating);
        response.put("comment", latestComment);
        response.put("ID", comment.getGid());
        response.put("posted", posted);
        response.put("name", game.getName());
        response.put("edited", isEdited);
        response.put("timestamp", new Date());

        // Return the response map with the latest review data
        return response;
    }
}
