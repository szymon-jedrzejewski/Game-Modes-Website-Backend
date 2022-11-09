package com.gmw.repository.sql.game;

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
public class GameSqlRepository implements Repository<Game> {

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
        LOGGER.debug("Updating the game with id: " + game.getId());
        persistenceManager.update(game);
    }

    @Override
    public void delete(Long id) {
        LOGGER.debug("Deleting game with id: " + id);
        persistenceManager.delete(id, "games");
    }

    @Override
    public List<Game> find(QuerySpec querySpec) throws SqlRepositoryException {
        try {
            return persistenceManager.find(querySpec).stream().map(persistable -> (Game)persistable).toList();
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during searching for values with QuerySpec: " + querySpec);
        }
        throw new SqlRepositoryException();
    }
}
