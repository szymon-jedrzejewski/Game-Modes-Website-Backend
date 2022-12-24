package com.gmw.services.fieldvalues.impl;

import com.gmw.fieldvalue.tos.ExistingFieldValueTO;
import com.gmw.fieldvalue.tos.NewFieldValueTO;
import com.gmw.fieldvalue.tos.SearchFieldValue;
import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.fieldvalues.DBFieldValueService;
import com.gmw.services.testutilities.ServiceType;
import com.gmw.services.testutilities.TestDbUtilities;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DBFieldValueServiceImplTest {
    private ServiceManager serviceManager;

    @Before
    public void setUp() {
        TestDbUtilities.initializeDatabase(ServiceType.FIELD_VALUE);
        this.serviceManager = new SqlServiceManager(new SqlRepositoryManager(TestDbUtilities.getConnection()));
    }

    @Test
    public void obtainModsIsBySearchValues() throws ResourceNotFoundException {
        SearchFieldValue searchFieldValue = new SearchFieldValue();
        searchFieldValue.setFieldId(1L);
        searchFieldValue.setValue("Test field value");
        SearchFieldValue searchFieldValue1 = new SearchFieldValue();
        searchFieldValue1.setFieldId(2L);
        List<Long> longs = serviceManager.getDbFieldValueService().obtainModsIdsBySearchValues(List.of(searchFieldValue, searchFieldValue1));
        assertEquals(1, longs.size());
        assertEquals(Long.valueOf(1), longs.get(0));
    }

    @Test
    public void obtainFieldValuesIdsByModId() throws ResourceNotFoundException {
        Long modId = 1L;
        List<Long> longs = serviceManager.getDbFieldValueService().obtainFieldValuesIdsByModId(modId);
        assertEquals(1, longs.size());
        assertEquals(Long.valueOf(1), longs.get(0));
    }

    @Test
    public void obtainModsIsBySearchValues_exactMatchBasicTest_shouldFindTwo() throws ResourceNotFoundException {
        SearchFieldValue searchFieldValue = new SearchFieldValue();
        searchFieldValue.setFieldId(1L);
        searchFieldValue.setValue("Some random field text");
        searchFieldValue.setExact(true);
        SearchFieldValue searchFieldValue1 = new SearchFieldValue();
        searchFieldValue1.setFieldId(1L);
        searchFieldValue1.setValue("Test field value");
        searchFieldValue1.setExact(true);
        List<Long> longs = serviceManager.getDbFieldValueService().obtainModsIdsBySearchValues(List.of(searchFieldValue, searchFieldValue1));
        assertEquals(2, longs.size());
        assertEquals(Long.valueOf(1), longs.get(0));
        assertEquals(Long.valueOf(3), longs.get(1));
    }

    @Test
    public void obtainModsIsBySearchValues_exactAndNotExactMatchBasicTest_shouldFindTwo() throws ResourceNotFoundException {
        SearchFieldValue searchFieldValue = new SearchFieldValue();
        searchFieldValue.setFieldId(1L);
        searchFieldValue.setValue("Some random field text");
        searchFieldValue.setExact(true);
        SearchFieldValue searchFieldValue1 = new SearchFieldValue();
        searchFieldValue1.setFieldId(1L);
        searchFieldValue1.setValue("value");
        searchFieldValue1.setExact(false);
        List<Long> longs = serviceManager.getDbFieldValueService().obtainModsIdsBySearchValues(List.of(searchFieldValue, searchFieldValue1));
        assertEquals(2, longs.size());
        assertEquals(Long.valueOf(1), longs.get(0));
        assertEquals(Long.valueOf(3), longs.get(1));
    }

    @Test
    public void obtainModsIsBySearchValues_notExactMatchBasicTest() throws ResourceNotFoundException {
        SearchFieldValue searchFieldValue = new SearchFieldValue();
        searchFieldValue.setFieldId(1L);
        searchFieldValue.setValue("field");
        searchFieldValue.setExact(false);
        List<Long> longs = serviceManager.getDbFieldValueService().obtainModsIdsBySearchValues(List.of(searchFieldValue));
        assertEquals(2, longs.size());
        assertEquals(Long.valueOf(1), longs.get(0));
        assertEquals(Long.valueOf(3), longs.get(1));
    }

    @Test
    public void obtainModsIsBySearchValues_notExactMatchBasicTest_shouldFindOne() throws ResourceNotFoundException {
        SearchFieldValue searchFieldValue = new SearchFieldValue();
        searchFieldValue.setFieldId(1L);
        searchFieldValue.setValue("some");
        searchFieldValue.setExact(false);
        List<Long> longs = serviceManager.getDbFieldValueService().obtainModsIdsBySearchValues(List.of(searchFieldValue));
        assertEquals(1, longs.size());
        assertEquals(Long.valueOf(3), longs.get(0));
    }

    @Test
    public void obtainModsIsBySearchValues_notExactMatchBasicTest_shouldFindTwo() throws ResourceNotFoundException {
        SearchFieldValue searchFieldValue = new SearchFieldValue();
        searchFieldValue.setFieldId(1L);
        searchFieldValue.setValue("some");
        searchFieldValue.setExact(false);
        SearchFieldValue searchFieldValue1 = new SearchFieldValue();
        searchFieldValue1.setFieldId(1L);
        searchFieldValue1.setValue("value");
        searchFieldValue1.setExact(false);
        List<Long> longs = serviceManager.getDbFieldValueService().obtainModsIdsBySearchValues(List.of(searchFieldValue, searchFieldValue1));
        assertEquals(2, longs.size());
        assertEquals(Long.valueOf(1), longs.get(0));
        assertEquals(Long.valueOf(3), longs.get(1));
    }

    @Test
    public void createFieldValue() throws ResourceNotCreatedException, ResourceNotFoundException {
        DBFieldValueService service = serviceManager.getDbFieldValueService();
        NewFieldValueTO newFieldValueTO = new NewFieldValueTO(1L, 2L, "random value");

        SearchFieldValue searchFieldValue = new SearchFieldValue();
        searchFieldValue.setFieldId(1L);
        searchFieldValue.setValue("Test field value");
        searchFieldValue.setExact(true);
        SearchFieldValue searchFieldValue1 = new SearchFieldValue();
        searchFieldValue1.setFieldId(1L);
        searchFieldValue1.setValue("random value");
        searchFieldValue1.setExact(true);

        List<Long> beforeCreation = service.obtainModsIdsBySearchValues(List.of(searchFieldValue, searchFieldValue1));
        assertEquals(1, beforeCreation.size());

        service.createFieldValue(newFieldValueTO);

        List<Long> afterCreation = service.obtainModsIdsBySearchValues(List.of(searchFieldValue1, searchFieldValue));
        assertEquals(2, afterCreation.size());
    }

    @Test
    public void updateFieldValue() throws ResourceNotUpdatedException, ResourceNotFoundException {
        DBFieldValueService service = serviceManager.getDbFieldValueService();
        ExistingFieldValueTO existingFieldValueTO = ExistingFieldValueTO
                .builder()
                .id(1L)
                .value("updated value")
                .fieldId(1L)
                .modId(2L)
                .build();

        SearchFieldValue searchFieldValue = new SearchFieldValue();
        searchFieldValue.setFieldId(1L);
        searchFieldValue.setValue("Test field value");
        searchFieldValue.setExact(true);

        List<Long> before = service.obtainModsIdsBySearchValues(List.of(searchFieldValue));
        assertEquals(1, before.size());

        searchFieldValue.setValue("updated value");
        service.updateFieldValue(existingFieldValueTO);

        List<Long> after = service.obtainModsIdsBySearchValues(List.of(searchFieldValue));
        assertEquals(1, after.size());

    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteFieldValue() throws ResourceNotDeletedException, ResourceNotFoundException {
        DBFieldValueService service = serviceManager.getDbFieldValueService();

        SearchFieldValue searchFieldValue = new SearchFieldValue();
        searchFieldValue.setFieldId(1L);
        searchFieldValue.setValue("Test field value");
        searchFieldValue.setExact(true);

        List<Long> before = service.obtainModsIdsBySearchValues(List.of(searchFieldValue));
        assertEquals(1, before.size());

        service.deleteFieldValue(1L);

        service.obtainModsIdsBySearchValues(List.of(searchFieldValue));
    }
}