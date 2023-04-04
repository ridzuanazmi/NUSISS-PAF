package nusiss.paf.revisionday28workshop.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.revisionday28workshop.Model.GameRatingDTO;
import nusiss.paf.revisionday28workshop.Model.GameReviewDTO;
import nusiss.paf.revisionday28workshop.Service.GameCommentService;

@RestController
public class GameCommentController {

    @Autowired
    private GameCommentService gcSrvs;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy.HH:mm:ss");

    @GetMapping(path = "/game/{game_id}/reviews", produces = "application/json")
    public ResponseEntity<?> getGameWithReviews(
            @PathVariable("game_id") int gameId) {

        GameReviewDTO gameReviews = gcSrvs.getGameReviews(gameId);

        if (gameReviews == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Game not found with the provided game_id");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(gameReviews);
        }
    }

    @GetMapping(path = "/games/{hl}", produces = "application/json")
    public ResponseEntity<?> getGamesWithHighestLowestRatings(
            @PathVariable("hl") String hl) {
        
        String decider = hl.toLowerCase();
        List<Document> gameList = gcSrvs.getHighestRatedComment(decider);

        if (gameList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not get list");
        } else {
            String now = sdf.format(new Date());

            GameRatingDTO gameRating = new GameRatingDTO();
            gameRating.setGames(gameList);
            gameRating.setRating(hl);
            gameRating.setTimestamp(now);

            return ResponseEntity.ok()
                .body(gameRating);
        }

    }
}
