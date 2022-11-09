package com.gmw.services.game;

import com.gmw.game.tos.ExistingGameTO;
import com.gmw.game.tos.NewGameTO;

public interface DBGameService {
    void createGame(NewGameTO game);
    void deleteGame(Long gameId);
    void updateGame(ExistingGameTO game);
}
