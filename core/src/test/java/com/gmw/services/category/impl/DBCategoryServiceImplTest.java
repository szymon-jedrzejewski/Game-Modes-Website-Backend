package com.gmw.services.category.impl;

import com.gmw.category.tos.ExistingCategoryTO;
import com.gmw.category.tos.NewCategoryTO;
import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.category.DBCategoryService;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.testutilities.ServiceType;
import com.gmw.services.testutilities.TestDbUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DBCategoryServiceImplTest {

    private ServiceManager serviceManager;

    @Before
    public void setUp() {
        TestDbUtilities.initializeDatabase(ServiceType.CATEGORY);
        this.serviceManager = new SqlServiceManager(new SqlRepositoryManager(TestDbUtilities.getConnection()));
    }

    @After
    public void tearDown() {
        TestDbUtilities.dropTables(ServiceType.CATEGORY);
    }

    @Test
    public void obtainCategories() throws ResourceNotFoundException {
        List<ExistingCategoryTO> existingCategoryTOS = serviceManager.getDbCategoryService().obtainCategories();

        assertNotNull(existingCategoryTOS);
        assertFalse(existingCategoryTOS.isEmpty());
        assertEquals(Long.valueOf(1), existingCategoryTOS.get(0).getId());
    }

    @Test
    public void obtainCategoryById() throws ResourceNotFoundException {
        ExistingCategoryTO existingCategoryTO = serviceManager.getDbCategoryService().obtainCategoryById(1L);

        assertNotNull(existingCategoryTO);
        assertEquals(Long.valueOf(1), existingCategoryTO.getId());
        assertEquals("category_test_name", existingCategoryTO.getName());
    }

    @Test
    public void obtainCategoryByName() throws ResourceNotFoundException {
        ExistingCategoryTO existingCategoryTO = serviceManager.getDbCategoryService().obtainCategoryByName("category_test_name");

        assertNotNull(existingCategoryTO);
        assertEquals(Long.valueOf(1), existingCategoryTO.getId());
        assertEquals("category_test_name", existingCategoryTO.getName());
    }

    @Test
    public void createCategory() throws ResourceNotCreatedException, ResourceNotFoundException {
        NewCategoryTO newCategoryTO = new NewCategoryTO();
        newCategoryTO.setName("new_category");

        DBCategoryService service = serviceManager.getDbCategoryService();
        service.createCategory(newCategoryTO);
        ExistingCategoryTO existingCategoryTO = service.obtainCategoryById(2L);

        assertNotNull(existingCategoryTO);
        assertEquals(Long.valueOf(2), existingCategoryTO.getId());
        assertEquals("new_category", existingCategoryTO.getName());
    }

    @Test
    public void updateCategory() throws ResourceNotUpdatedException, ResourceNotFoundException {
        ExistingCategoryTO expected = ExistingCategoryTO
                .builder()
                .id(1L)
                .name("change_name")
                .build();

        DBCategoryService service = serviceManager.getDbCategoryService();
        service.updateCategory(expected);
        ExistingCategoryTO existingCategoryTO = service.obtainCategoryById(1L);

        assertNotNull(existingCategoryTO);
        assertEquals(expected, existingCategoryTO);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteCategory() throws ResourceNotDeletedException, ResourceNotFoundException {
        DBCategoryService service = serviceManager.getDbCategoryService();
        service.deleteCategory(1L);
        service.obtainCategoryById(1L);
    }
}