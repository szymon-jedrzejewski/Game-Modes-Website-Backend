package com.gmw.services.game.impl;

import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.game.DBGameReadService;
import com.gmw.model.Game;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.DBService;
import com.gmw.game.tos.ExistingGameTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DBGameReadServiceImpl extends DBService implements DBGameReadService {

    private static final Logger LOGGER = LogManager.getLogger();

    public DBGameReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public ExistingGameTO obtainGameById(Long id) throws ResourceNotFoundException {
        Repository<Game> gameRepositoryManager = getRepositoryManager().getGameRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("games");
        querySpec.setClazz(Game.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, id));

        try {
            List<Game> games = gameRepositoryManager.find(querySpec);
            return mapExistingGames(games);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Error during obtaining game with id: " + id, e);
        }
        throw new ResourceNotFoundException();
    }

    @Override
    public ExistingGameTO obtainGameByName(String name) throws ResourceNotFoundException {
        Repository<Game> gameRepositoryManager = getRepositoryManager().getGameRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("games");
        querySpec.setClazz(Game.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("name", Operator.EQUAL_TO, name));

        try {
            List<Game> games = gameRepositoryManager.find(querySpec);
            return mapExistingGames(games);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Error during obtaining game with name: " + name);
        }

        throw new ResourceNotFoundException();
    }

    @Override
    public List<ExistingGameTO> obtainAllGames() throws ResourceNotFoundException {
        Repository<Game> gameRepositoryManager = getRepositoryManager().getGameRepository();
        QuerySpec querySpec = new QuerySpec();
        querySpec.setClazz(Game.class);
        querySpec.setTableName("games");

        try {
            return gameRepositoryManager
                    .find(querySpec)
                    .stream()
                    .map(this::mapExistingGame)
                    .toList();
        } catch (SqlRepositoryException e) {
            LOGGER.error("Error during obtaining all games", e);
        }

        throw new ResourceNotFoundException();
    }

    private ExistingGameTO mapExistingGames(List<Game> games) {
        if (games != null && !games.isEmpty()) {
            Game game = games.get(0);
            return mapExistingGame(game);
        }

        return null;
    }

    private ExistingGameTO mapExistingGame(Game game) {
        return ExistingGameTO
                .builder()
                .id(game.getId())
                .name(game.getName())
                .description(game.getDescription())
                .avatar(game.getAvatar())
                .build();
    }
}
