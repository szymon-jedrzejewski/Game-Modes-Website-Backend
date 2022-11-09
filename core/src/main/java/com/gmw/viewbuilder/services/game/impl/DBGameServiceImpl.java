package com.gmw.viewbuilder.services.game.impl;

import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.Game;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.viewbuilder.services.game.DBGameService;
import com.gmw.viewbuilder.tos.ExistingGameTO;
import com.gmw.viewbuilder.tos.NewGameTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBGameServiceImpl extends DBGameReadServiceImpl implements DBGameService {

    private static final Logger LOGGER = LogManager.getLogger();

    public DBGameServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createGame(NewGameTO newGame) {
        try {
            Repository<Game> gameRepositoryManager = getRepositoryManager().getGameRepositoryManager();

            Game game = new Game("games");
            game.setName(newGame.getName());
            game.setDescription(newGame.getDescription());
            game.setAvatar(newGame.getAvatar());

            gameRepositoryManager.create(game);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Error during creating new game!");
        }
    }

    @Override
    public void deleteGame(Long gameId) {
        getRepositoryManager().getGameRepositoryManager().delete(gameId);
    }

    @Override
    public void updateGame(ExistingGameTO existingGameTO) {
        Game game = new Game("games");
        game.setId(existingGameTO.getId());
        game.setName(existingGameTO.getName());
        game.setDescription(existingGameTO.getDescription());
        game.setAvatar(existingGameTO.getAvatar());

        getRepositoryManager().getGameRepositoryManager().update(game);
    }
}
