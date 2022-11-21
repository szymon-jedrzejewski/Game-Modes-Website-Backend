package com.gmw.services.game;

import com.gmw.game.tos.ExistingGameTO;

import java.util.List;

public interface DBGameReadService {
    ExistingGameTO obtainGameById(Long id);
    ExistingGameTO obtainGameByName(String name);

    List<ExistingGameTO> obtainAllGames();
}
