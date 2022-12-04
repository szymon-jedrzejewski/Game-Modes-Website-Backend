package com.gmw.services.game;

import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.game.tos.ExistingGameTO;
import com.gmw.game.tos.NewGameTO;

public interface DBGameService {
    void createGame(NewGameTO game) throws ResourceNotCreatedException;
    void deleteGame(Long gameId) throws ResourceNotDeletedException;
    void updateGame(ExistingGameTO game) throws ResourceNotUpdatedException;
}
