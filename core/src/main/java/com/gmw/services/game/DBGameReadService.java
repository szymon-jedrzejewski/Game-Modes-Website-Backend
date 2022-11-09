package com.gmw.services.game;

import com.gmw.game.tos.ExistingGameTO;

public interface DBGameReadService {
    ExistingGameTO obtainGameById(Long id);
    ExistingGameTO obtainGameByName(String name);
}
