package com.gmw.services.game.impl;

import com.gmw.game.tos.ExistingGameTO;
import com.gmw.game.tos.NewGameTO;
import com.gmw.model.Game;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
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

        Repository<Game> repository = getRepositoryManager().getGameRepository();

        Game game = new Game("games");
        game.setName(newGame.getName());
        game.setDescription(newGame.getDescription());
        game.setAvatar(newGame.getAvatar());

        ServiceUtils.create(repository, game);
    }

    @Override
    public void deleteGame(Long gameId) throws ResourceNotDeletedException {
        Repository<Game> repository = getRepositoryManager().getGameRepository();
        ServiceUtils.delete(repository, gameId);
    }

    @Override
    public void updateGame(ExistingGameTO existingGameTO) throws ResourceNotUpdatedException {
        Game game = new Game("games");
        game.setId(existingGameTO.getId());
        game.setName(existingGameTO.getName());
        game.setDescription(existingGameTO.getDescription());
        game.setAvatar(existingGameTO.getAvatar());

        Repository<Game> repository = getRepositoryManager().getGameRepository();

        ServiceUtils.update(repository, game);
    }
}
