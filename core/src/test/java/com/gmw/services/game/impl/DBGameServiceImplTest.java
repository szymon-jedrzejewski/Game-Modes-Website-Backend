package com.gmw.services.game.impl;

import com.gmw.game.tos.ExistingGameTO;
import com.gmw.game.tos.NewGameTO;
import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.game.DBGameService;
import com.gmw.services.testutilities.ServiceType;
import com.gmw.services.testutilities.TestDbUtilities;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBGameServiceImplTest {
    private ServiceManager serviceManager;

    @Before
    public void setUp() {
        TestDbUtilities.initializeDatabase(ServiceType.GAME);
        this.serviceManager = new SqlServiceManager(new SqlRepositoryManager(TestDbUtilities.getConnection()));
    }

    @Test
    public void obtainGameById() throws ResourceNotFoundException {
        ExistingGameTO actual = serviceManager.getDbGameService().obtainGameById(1L);
        ExistingGameTO expected = prepareGame("Game description test", "game_test_name", 1L);

        assertEquals(expected, actual);
    }

    @Test
    public void obtainGameByName() throws ResourceNotFoundException {
        ExistingGameTO actual = serviceManager.getDbGameService().obtainGameByName("game_test_name");
        ExistingGameTO expected = prepareGame("Game description test", "game_test_name", 1L);

        assertEquals(expected, actual);
    }

    @Test
    public void obtainAllGames() throws ResourceNotFoundException {
        ExistingGameTO actual = serviceManager.getDbGameService().obtainAllGames().get(0);
        ExistingGameTO expected = prepareGame("Game description test", "game_test_name", 1L);

        assertEquals(expected, actual);
    }

    @Test
    public void createGame() throws ResourceNotCreatedException, ResourceNotFoundException {
        DBGameService service = serviceManager.getDbGameService();

        NewGameTO game = new NewGameTO();
        game.setAvatar(null);
        game.setName("New game");
        game.setDescription("New description");

        service.createGame(game);

        ExistingGameTO actual = service.obtainGameById(2L);
        ExistingGameTO expected = prepareGame("New description", "New game", 2L);

        assertEquals(expected, actual);
    }

    @Test
    public void updateGame() throws ResourceNotFoundException, ResourceNotUpdatedException {
        DBGameService service = serviceManager.getDbGameService();

        ExistingGameTO expected = prepareGame("Updated description", "Updated game", 1L);
        service.updateGame(expected);

        ExistingGameTO actual = service.obtainGameById(1L);

        assertEquals(expected, actual);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteGame() throws ResourceNotFoundException, ResourceNotDeletedException {
        DBGameService service = serviceManager.getDbGameService();
        ExistingGameTO actual = serviceManager.getDbGameService().obtainGameById(1L);
        ExistingGameTO expected = prepareGame("Game description test", "game_test_name", 1L);

        assertEquals(expected, actual);

        service.deleteGame(1L);

        serviceManager.getDbGameService().obtainGameById(1L);
    }

    private ExistingGameTO prepareGame(String description, String name, Long id) {
        return ExistingGameTO
                .builder()
                .id(id)
                .description(description)
                .avatar(null)
                .name(name)
                .build();
    }
}