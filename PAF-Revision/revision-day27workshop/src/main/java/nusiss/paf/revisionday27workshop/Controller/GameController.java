package nusiss.paf.revisionday27workshop.Controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.revisionday27workshop.Model.Comment;
import nusiss.paf.revisionday27workshop.Model.Game;
import nusiss.paf.revisionday27workshop.Service.CommentService;

@RestController
public class GameController {

    @Autowired
    private CommentService cSrvc;

    @PostMapping(path = "/review", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public ResponseEntity<?> addReview(
            @RequestBody MultiValueMap<String, String> formData) {

        // Captures the value of the formData with the key(fields)
        String name = formData.getFirst("name");
        int rating = Integer.parseInt(formData.getFirst("rating"));
        String comment = formData.getFirst("comment");
        int gameId = Integer.parseInt(formData.getFirst("gid"));

        // Creates a UUID whenever a new review is posted and pass it into cSrvc
        String c_id = UUID.randomUUID().toString().substring(0, 8);

        // Check if gameId is valid and retrieve the game. null if invalid
        Game game = cSrvc.getGameById(gameId);

        // Checks the value of rating and if "gid" exists
        if (null == game) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid gid value");
        } else if (rating < 0 || rating > 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid rating value. 0 - 10 inclusive");
        }

        // Adds the review into MongoDB
        Comment review = null;
        if (game != null && rating >= 0 && rating <= 10) {
            review = cSrvc.addComment(name, rating, comment, gameId, c_id);
        }
        // Returns appropiate response
        if (null == review) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to post review/comment");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(review);
        }
    }

    @PutMapping(path = "/review/{review_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateReview(
            @PathVariable("review_id") String reviewId,
            @RequestBody Map<String, Object> updateForm) {

        Integer rating = (Integer) updateForm.get("rating");
        String comment = (String) updateForm.get("comment");

        if (rating == null || (rating < 0 || rating > 10)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid rating value. Rating should be 0 - 10 inclusive");
        }

        Comment updatedComment = cSrvc.updateComment(reviewId, rating, comment);

        if (null == updatedComment) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not update the review due to an invalid review id");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(updatedComment);
        }
    }

    @GetMapping(path = "/review/{review_id}", produces = "application/json")
    public ResponseEntity<?> getLatestReview(
            @PathVariable("review_id") String reviewId) {

        // Calls the helper class
        Map<String, Object> latestReviewData = cSrvc.getLatestReviewData(reviewId);

        if (latestReviewData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not find the review with the provided review id");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(latestReviewData);
    }

    @GetMapping(path = "/review/{review_id}/history", produces = "application/json")
    public ResponseEntity<?> getReviewHistory(
            @PathVariable("review_id") String reviewId) {

        // Call the service method to fetch the Comment object with the given reviewId
        Comment reviewHistory = cSrvc.getCommentById(reviewId);

        if (reviewHistory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Review not found with the provided review_id");
        } else {
            // Call the service method to fetch the Game object with reviewId
            Game game = cSrvc.getGameById(reviewHistory.getGid());
            // Set the game_name to match the gid from game object/collection
            reviewHistory.setGame_name(game.getName());
            return ResponseEntity.status(HttpStatus.OK).body(reviewHistory);
        }
    }
}
