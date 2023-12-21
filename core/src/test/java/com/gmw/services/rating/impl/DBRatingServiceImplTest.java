package com.gmw.services.rating.impl;

import com.gmw.rating.tos.ExistingRatingTO;
import com.gmw.rating.tos.NewRatingTO;
import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.rating.DBRatingService;
import com.gmw.services.testutilities.ServiceType;
import com.gmw.services.testutilities.TestDbUtilities;
import com.gmw.user.tos.NewUserTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DBRatingServiceImplTest {

    private ServiceManager serviceManager;

    @BeforeEach
    void setUp() {
        TestDbUtilities.initializeDatabase(ServiceType.RATING);
        this.serviceManager = new SqlServiceManager(new SqlRepositoryManager(TestDbUtilities.getConnection()));
    }

    @Test
    void obtainRatingForMod() {
        Double rating = serviceManager.getDbRatingService().obtainRatingForMod(1L);
        assertEquals(5.0, rating);
    }

    @Test
    void obtainRatingsIdsByModId() throws ResourceNotFoundException {
        List<Long> ratingsIds = serviceManager.getDbRatingService().obtainRatingsIdsByModId(1L);
        assertEquals(1, ratingsIds.size());
        assertEquals(1, ratingsIds.get(0));
    }

    @Test
    void obtainUserIdByRatingId() throws ResourceNotFoundException {
        Long userId = serviceManager.getDbRatingService().obtainUserIdByRatingId(1L);
        assertEquals(1L, userId);
    }

    @Test
    void createRating() throws ResourceNotCreatedException {
        DBRatingService service = serviceManager.getDbRatingService();
        serviceManager.getDbUserService().createUser(new NewUserTO());

        NewRatingTO rating = new NewRatingTO();
        rating.setRating(3);
        rating.setModId(1L);
        rating.setUserId(2L);

        service.createRating(rating);

        assertEquals(4L, service.obtainRatingForMod(1L));
    }

    @Test
    void updateRating() throws ResourceNotUpdatedException {
        DBRatingService service = serviceManager.getDbRatingService();
        ExistingRatingTO rating = ExistingRatingTO
                .builder()
                .id(1L)
                .userId(1L)
                .modId(1L)
                .rating(3)
                .build();

        service.updateRating(rating);

        assertEquals(3L, service.obtainRatingForMod(1L));
    }

    @Test
    void deleteRating() throws ResourceNotDeletedException {
        DBRatingService service = serviceManager.getDbRatingService();
        service.deleteRating(1L);

        Double actual = service.obtainRatingForMod(1L);

        assertEquals(0, actual);
    }
}