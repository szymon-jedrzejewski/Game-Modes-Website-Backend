package com.gmw.api.rest;

import com.gmw.api.rest.activity.game.*;
import com.gmw.game.tos.ExistingGameTO;
import com.gmw.game.tos.NewGameTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class GameController {

    @GetMapping("/findAll")
    public ResponseEntity<List<ExistingGameTO>> findAllGames() {
        FindAllGamesActivity activity = new FindAllGamesActivity();
        return activity.execute();
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<ExistingGameTO> findGameByName(@PathVariable String name) {
        FindGameByNameActivity activity = new FindGameByNameActivity(name);
        return activity.execute();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ExistingGameTO> findGameById(@PathVariable Long id) {
        FindGameByIdActivity activity = new FindGameByIdActivity(id);
        return activity.execute();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createGame(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                           @RequestBody NewGameTO game) {
        CreateGameActivity activity = new CreateGameActivity(game, token);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateGame(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                           @RequestBody ExistingGameTO existingGameTO) {
        UpdateGameActivity activity = new UpdateGameActivity(existingGameTO, token);
        return activity.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGame(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                           @PathVariable Long id) {
        DeleteGameActivity activity = new DeleteGameActivity(id, token);
        return activity.execute();
    }
}
