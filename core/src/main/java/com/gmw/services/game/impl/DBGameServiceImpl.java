package com.gmw.services.game.impl;

import com.gmw.coverters.GameConverter;
import com.gmw.coverters.TOConverter;
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

public class DBGameServiceImpl extends DBGameReadServiceImpl implements DBGameService {

    public DBGameServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createGame(NewGameTO newGame) throws ResourceNotCreatedException {

        Repository<Game> repository = getRepositoryManager().getGameRepository();
        TOConverter<NewGameTO, Game> converter = new GameConverter();

        Game game = converter.convertToModel(newGame);

        ServiceUtils.create(repository, game);
    }

    @Override
    public void deleteGame(Long gameId) throws ResourceNotDeletedException {
        Repository<Game> repository = getRepositoryManager().getGameRepository();
        ServiceUtils.delete(repository, gameId);
    }

    @Override
    public void updateGame(ExistingGameTO existingGameTO) throws ResourceNotUpdatedException {
        Repository<Game> repository = getRepositoryManager().getGameRepository();
        TOConverter<NewGameTO, Game> converter = new GameConverter();

        Game game = converter.convertToModel(existingGameTO);
        game.setId(existingGameTO.getId());

        ServiceUtils.update(repository, game);
    }
}
