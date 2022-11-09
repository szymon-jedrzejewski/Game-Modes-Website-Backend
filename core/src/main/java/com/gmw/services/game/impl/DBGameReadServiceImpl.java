package com.gmw.services.game.impl;

import com.gmw.exceptions.SqlRepositoryException;
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
    public ExistingGameTO obtainGameById(Long id) {
        Repository<Game> gameRepositoryManager = getRepositoryManager().getGameRepositoryManager();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("games");
        querySpec.setClazz(Game.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, id));

        try {
            List<Game> games = gameRepositoryManager.find(querySpec);
            return mapExistingGames(games);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Error during obtaining game with id: " + id);
        }

        return null;
    }

    @Override
    public ExistingGameTO obtainGameByName(String name) {
        Repository<Game> gameRepositoryManager = getRepositoryManager().getGameRepositoryManager();

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

        return null;
    }

    private ExistingGameTO mapExistingGames(List<Game> games) {
        if (games != null && !games.isEmpty()) {
            Game game = games.get(0);
            return ExistingGameTO
                    .builder()
                    .id(game.getId())
                    .name(game.getName())
                    .description(game.getDescription())
                    .avatar(game.getAvatar())
                    .build();
        }

        return null;
    }
}
