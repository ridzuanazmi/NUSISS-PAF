package nusiss.paf.revisionday26workshop.Service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.JsonObject;
import nusiss.paf.revisionday26workshop.Repository.BoardgamesRepository;

@Service
public class BoardgameService {
    
    @Autowired
    private BoardgamesRepository bRepo;

    public JsonObject getGames(Integer limit, Integer offset) {
        return bRepo.getGames(limit, offset);
    }

    public JsonObject getGamesRank(int limit, int offset) {
        return bRepo.getGamesRank(limit, offset);
    }

    public List<Document> getGames2(Integer limit, Integer offset) {
        return bRepo.getGames2(limit, offset);
    } 

    public JsonObject getGameById(Integer gameId) {
        return bRepo.getGameById(gameId);
    }
}
