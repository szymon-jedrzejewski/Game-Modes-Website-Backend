package com.gmw.services.game.impl;

import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.game.tos.ExistingGameTO;
import com.gmw.game.tos.NewGameTO;
import com.gmw.model.Game;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.game.DBGameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBGameServiceImpl extends DBGameReadServiceImpl implements DBGameService {

    private static final Logger LOGGER = LogManager.getLogger();

    public DBGameServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createGame(NewGameTO newGame) throws ResourceNotCreatedException {
        try {
            Repository<Game> gameRepositoryManager = getRepositoryManager().getGameRepository();

            Game game = new Game("games");
            game.setName(newGame.getName());
            game.setDescription(newGame.getDescription());
            game.setAvatar(newGame.getAvatar());

            Long gameId = gameRepositoryManager.create(game);

            if (gameId == null)
            {
                throw new ResourceNotCreatedException();
            }

        } catch (SqlRepositoryException e) {
            LOGGER.error("Error during creating new game!");
        }
    }

    @Override
    public void deleteGame(Long gameId) throws ResourceNotDeletedException {
        Repository<Game> repository = getRepositoryManager().getGameRepository();
        try {
            repository.delete(gameId);
        } catch (SqlRepositoryException e) {
            throw new ResourceNotDeletedException(e);
        }
    }

    @Override
    public void updateGame(ExistingGameTO existingGameTO) throws ResourceNotUpdatedException {
        Game game = new Game("games");
        game.setId(existingGameTO.getId());
        game.setName(existingGameTO.getName());
        game.setDescription(existingGameTO.getDescription());
        game.setAvatar(existingGameTO.getAvatar());

        try {
            getRepositoryManager().getGameRepository().update(game);
        } catch (SqlRepositoryException e) {
            throw new ResourceNotUpdatedException(e);
        }
    }
}
