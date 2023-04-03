package nusiss.paf.revisionday26workshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import nusiss.paf.revisionday26workshop.Service.BoardgameService;

@RestController
public class BoardgameController {

    @Autowired
    private BoardgameService bSrvc;

    @GetMapping(path = "/games", produces = "application/json")
    public ResponseEntity<?> getGames(
            @RequestParam(required = false, defaultValue = "25") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer offset) {

        JsonObject gameList = bSrvc.getGames(limit, offset);
        // List<Boardgame> game = bSrvc.getGames(limit, offset);

        if (gameList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("COuld not find game list");
        } else {
            return ResponseEntity.ok()
                    .body(gameList.toString());
        }
    }

    @GetMapping(path = "/games/rank", produces = "application/json")
    public ResponseEntity<?> getGamesRank(
            @RequestParam(required = false, defaultValue = "25") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer offset) {

        JsonObject gameList = bSrvc.getGamesRank(limit, offset);

        if (gameList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("COuld not find game list");
        } else {
            return ResponseEntity.ok()
                    .body(gameList.toString());
        }
    }

    @GetMapping(path = "game/{game_id}", produces = "application/json")
    public ResponseEntity<?> getGameById(@PathVariable("game_id") Integer gid) {

        JsonObject game = bSrvc.getGameById(gid);

        if (null == game) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Could not find game with id %d\n".formatted(gid));
        } else {
            return ResponseEntity.ok()
                .body(game.toString());
        }
    }

}
