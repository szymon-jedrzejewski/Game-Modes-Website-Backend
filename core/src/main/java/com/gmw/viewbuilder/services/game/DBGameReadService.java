package com.gmw.viewbuilder.services.game;

import com.gmw.viewbuilder.tos.ExistingGameTO;

public interface DBGameReadService {
    ExistingGameTO obtainGameById(Long id);
    ExistingGameTO obtainGameByName(String name);
}
