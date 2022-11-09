package com.gmw.viewbuilder.services.game;

import com.gmw.viewbuilder.tos.ExistingGameTO;
import com.gmw.viewbuilder.tos.NewGameTO;

public interface DBGameService {
    void createGame(NewGameTO game);
    void deleteGame(Long gameId);
    void updateGame(ExistingGameTO game);
}
