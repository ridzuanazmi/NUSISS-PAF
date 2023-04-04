package nusiss.paf.revisionday28workshop.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.revisionday28workshop.Model.Comment;
import nusiss.paf.revisionday28workshop.Model.Game;
import nusiss.paf.revisionday28workshop.Model.GameReviewDTO;
import nusiss.paf.revisionday28workshop.Repository.GameCommentRepository;

@Service
public class GameCommentService {

    @Autowired
    private GameCommentRepository gcRepo;

    public Game getGameById(Integer gid) {
        return gcRepo.getGameById(gid);
    }

    public Comment getCommentById(String _id) {
        return gcRepo.getCommentById(_id);
    }

    public List<Comment> getCommentByGid(Integer gid) {
        return gcRepo.getCommentByGid(gid);
    }

    public List<Document> getHighestRatedComment(String hl) {
        return gcRepo.getGamesWithHighestLowestRatings(hl);
    }

    // Method to return GameReviewDTO with "gid" to match the question
    public GameReviewDTO getGameReviews(Integer gameId) {
        // Query the game collection to find the game document by gameId
        Game game = getGameById(gameId);

        // Query the comment collection to find comments via gid
        List<Comment> reviews = getCommentByGid(gameId);

        // Map the Comment objects to their corresponding review URLs
        List<String> reviewUrls = reviews.stream()
                .filter(comment -> comment.getC_id() != null)
                .map(comment -> "http://localhost:8080/review/" + comment.get_id())
                .collect(Collectors.toList());

        // Get the average rating for the game from the comment collection using gid
        Double avgRating = gcRepo.getAvgRating(gameId);

        // Create the GameReviewsDTO object and populate the fields
        GameReviewDTO gameReviews = new GameReviewDTO();
        gameReviews.setGameId(game.getId());
        gameReviews.setName(game.getName());
        gameReviews.setYear(game.getYear());
        gameReviews.setRank(game.getRanking());
        gameReviews.setAverage(avgRating);
        gameReviews.setUsersRated(game.getUsers_rated());
        gameReviews.setUrl(game.getUrl());
        gameReviews.setThumbnail(game.getImage());
        gameReviews.setReviews(reviewUrls);
        gameReviews.setTimestamp(Instant.now().toString());

        return gameReviews;
    }
}
