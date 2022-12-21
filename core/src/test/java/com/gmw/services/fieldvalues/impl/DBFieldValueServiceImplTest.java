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
    public void obtainModsIsBySearchValues() throws ResourceNotDeletedException, ResourceNotFoundException {
        SearchFieldValue searchFieldValue = new SearchFieldValue();
        searchFieldValue.setFieldId(1L);
        searchFieldValue.setValue("Test field value");
        SearchFieldValue searchFieldValue1 = new SearchFieldValue();
        searchFieldValue1.setFieldId(2L);
        List<Long> longs = serviceManager.getDbFieldValueService().obtainModsIsBySearchValues(List.of(searchFieldValue, searchFieldValue1));
        assertEquals(1, longs.size());
        assertEquals(Long.valueOf(1), longs.get(0));
    }

    @Test
    public void createFieldValue() throws ResourceNotCreatedException, ResourceNotDeletedException, ResourceNotFoundException {
        DBFieldValueService service = serviceManager.getDbFieldValueService();
        NewFieldValueTO newFieldValueTO = new NewFieldValueTO(1L, 1L,"random value");

        SearchFieldValue searchFieldValue = new SearchFieldValue();
        searchFieldValue.setFieldId(1L);
        searchFieldValue.setValue("Test field value");
        SearchFieldValue searchFieldValue1 = new SearchFieldValue();
        searchFieldValue1.setFieldId(1L);
        searchFieldValue1.setValue("random value");

        List<Long> beforeCreation = service.obtainModsIsBySearchValues(List.of(searchFieldValue, searchFieldValue1));
        assertEquals(1, beforeCreation.size());

        service.createFieldValue(newFieldValueTO);

        List<Long> afterCreation = service.obtainModsIsBySearchValues(List.of(searchFieldValue1, searchFieldValue));
        assertEquals(2, afterCreation.size());
    }

    @Test
    public void updateFieldValue() throws ResourceNotUpdatedException, ResourceNotDeletedException, ResourceNotFoundException {
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

        List<Long> before = service.obtainModsIsBySearchValues(List.of(searchFieldValue));
        assertEquals(1, before.size());

        searchFieldValue.setValue("updated value");
        service.updateFieldValue(existingFieldValueTO);

        List<Long> after = service.obtainModsIsBySearchValues(List.of(searchFieldValue));
        assertEquals(1, after.size());

    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteFieldValue() throws ResourceNotDeletedException, ResourceNotFoundException {
        DBFieldValueService service = serviceManager.getDbFieldValueService();

        SearchFieldValue searchFieldValue = new SearchFieldValue();
        searchFieldValue.setFieldId(1L);
        searchFieldValue.setValue("Test field value");

        List<Long> before = service.obtainModsIsBySearchValues(List.of(searchFieldValue));
        assertEquals(1, before.size());

        service.deleteFieldValue(1L);

        service.obtainModsIsBySearchValues(List.of(searchFieldValue));
    }
}