package com.gmw.repository.game;

import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.Game;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.QuerySpec;
import com.gmw.repository.Repository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@AllArgsConstructor
public class GameRepository implements Repository<Game> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final PersistenceManager persistenceManager;

    @Override
    public Long create(Game newGame) throws SqlRepositoryException {
        try {
            LOGGER.debug("Creating new game!");
            Game game = (Game) persistenceManager.create(newGame);
            return game.getId();
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Can not create new game!");
            throw new SqlRepositoryException();
        }
    }

    @Override
    public void update(Game game) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Game> find(QuerySpec querySpec) throws SqlRepositoryException {
        return null;
    }
}
