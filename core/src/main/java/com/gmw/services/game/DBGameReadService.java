package com.gmw.services.game;

import com.gmw.game.tos.ExistingGameTO;
import com.gmw.services.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DBGameReadService {
    ExistingGameTO obtainGameById(Long id) throws ResourceNotFoundException;
    ExistingGameTO obtainGameByName(String name) throws ResourceNotFoundException;

    List<ExistingGameTO> obtainAllGames() throws ResourceNotFoundException;
}
