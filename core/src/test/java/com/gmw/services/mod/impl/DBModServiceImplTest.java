package com.gmw.services.mod.impl;

import com.gmw.mod.tos.ExistingModTO;
import com.gmw.mod.tos.NewModTO;
import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.mod.DBModService;
import com.gmw.services.testutilities.ServiceType;
import com.gmw.services.testutilities.TestDbUtilities;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class DBModServiceImplTest {
    private ServiceManager serviceManager;

    @Before
    public void setUp() {
        TestDbUtilities.initializeDatabase(ServiceType.MOD);
        this.serviceManager = new SqlServiceManager(new SqlRepositoryManager(TestDbUtilities.getConnection()));
    }

    @Test
    public void findModsByIds() throws ResourceNotFoundException {
        List<ExistingModTO> modsByIds = serviceManager.getDbModService().findModsByIds(List.of(1L, 2L));

        assertEquals(2, modsByIds.size());
    }

    @Test
    public void findModById() throws ResourceNotFoundException {
        ExistingModTO actual = serviceManager.getDbModService().findModById(1L);

        assertNotNull(actual);
        assertEquals(Long.valueOf(1L), actual.getId());
    }

    @Test
    public void findAllMods() throws ResourceNotFoundException {
        List<ExistingModTO> modsByIds = serviceManager.getDbModService().findAllMods();

        assertEquals(2, modsByIds.size());
    }

    @Test
    public void findModsByGameId() throws ResourceNotFoundException {
        List<ExistingModTO> modsByIds = serviceManager.getDbModService().findModsByGameId(1L);

        assertEquals(2, modsByIds.size());
    }

    @Test
    public void createMod() throws ResourceNotFoundException, ResourceNotCreatedException {
        DBModService service = serviceManager.getDbModService();
        NewModTO newModTO = new NewModTO();
        newModTO.setAvatar(null);
        newModTO.setDate(Date.valueOf(LocalDate.now()));
        newModTO.setDescription("some desc");
        newModTO.setName("OddWorld");
        newModTO.setGameId(1L);
        newModTO.setCategoryId(1L);
        newModTO.setUserId(1L);
        newModTO.setDownloadLink("www.github.com");

        List<ExistingModTO> before = service.findAllMods();
        assertEquals(2, before.size());

        service.createMod(newModTO);

        List<ExistingModTO> after = service.findAllMods();
        assertEquals(3, after.size());
        assertEquals(Long.valueOf(3), after.get(2).getId());

    }

    @Test
    public void updateMod() throws ResourceNotFoundException, ResourceNotUpdatedException {
        DBModService service = serviceManager.getDbModService();
        ExistingModTO mod = new ExistingModTO(1L,
                "BeatSaber",
                1L,
                1L,
                1L,
                "I wish beat saber was on ps vr2",
                "www.github.com",
                Date.valueOf(LocalDate.now()),
                null);


        List<ExistingModTO> before = service.findModsByIds(List.of(1L));
        assertEquals(1, before.size());
        assertNotEquals(mod, before.get(0));

        service.updateMod(mod);

        List<ExistingModTO> after = service.findModsByIds(List.of(1L));
        assertEquals(1, before.size());
        assertEquals(mod, after.get(0));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteMod() throws ResourceNotFoundException, ResourceNotDeletedException {
        DBModService service = serviceManager.getDbModService();

        List<ExistingModTO> before = service.findModsByIds(List.of(1L));
        assertEquals(1, before.size());

        service.deleteMod(1L);

        service.findModsByIds(List.of(1L));
    }
}