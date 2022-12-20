package com.gmw.services.view.impl;

import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.testutilities.ServiceType;
import com.gmw.services.testutilities.TestDbUtilities;
import com.gmw.services.view.DBViewService;
import com.gmw.view.tos.ExistingViewTO;
import com.gmw.view.tos.NewViewTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DBViewServiceImplTest {
    private ServiceManager serviceManager;

    @Before
    public void setUp() {
        TestDbUtilities.initializeDatabase(ServiceType.VIEW);
        this.serviceManager = new SqlServiceManager(new SqlRepositoryManager(TestDbUtilities.getConnection()));
    }

    @Test
    public void obtainViewById() throws ResourceNotFoundException {
        ExistingViewTO actual = serviceManager.getDbViewService().obtainViewById(1L);
        ExistingViewTO expected = prepareView(1L);

        assertEquals(expected, actual);
    }

    @Test
    public void obtainViewByGameId() throws ResourceNotFoundException {
        ExistingViewTO actual = serviceManager.getDbViewService().obtainViewByGameId(1L);
        ExistingViewTO expected = prepareView(1L);

        assertEquals(expected, actual);
    }

    @Test
    public void createView() throws ResourceNotCreatedException, ResourceNotFoundException {
        DBViewService service = serviceManager.getDbViewService();

        NewViewTO newViewTO = new NewViewTO();
        newViewTO.setGameId(1L);

        service.createView(newViewTO);
        ExistingViewTO actual = service.obtainViewById(2L);

        assertEquals(prepareView(2L), actual);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteView() throws ResourceNotFoundException, ResourceNotDeletedException {
        DBViewService service = serviceManager.getDbViewService();
        ExistingViewTO actual = service.obtainViewByGameId(1L);
        ExistingViewTO expected = prepareView(1L);

        assertEquals(expected, actual);

        service.deleteView(1L);
        service.obtainViewById(1L);
    }

    private ExistingViewTO prepareView(Long id) {
        return ExistingViewTO
                .builder()
                .id(id)
                .gameId(1L)
                .build();
    }
}