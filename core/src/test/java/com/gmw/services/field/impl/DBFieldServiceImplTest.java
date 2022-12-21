package com.gmw.services.field.impl;

import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.field.DBFieldService;
import com.gmw.services.testutilities.ServiceType;
import com.gmw.services.testutilities.TestDbUtilities;
import com.gmw.field.enums.FieldTypeEnum;
import com.gmw.field.tos.ExistingFieldTO;
import com.gmw.field.tos.NewFieldTO;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DBFieldServiceImplTest {
    private ServiceManager serviceManager;

    @Before
    public void setUp() {
        TestDbUtilities.initializeDatabase(ServiceType.FIELD);
        this.serviceManager = new SqlServiceManager(new SqlRepositoryManager(TestDbUtilities.getConnection()));
    }

    @Test
    public void obtainFieldsByViewId() throws ResourceNotFoundException {
        ExistingFieldTO actual = serviceManager.getDbFieldService().obtainFieldsByViewId(1L).get(0);
        ExistingFieldTO expected = prepareField("Text field", FieldTypeEnum.TEXT, "Field test description");

        assertEquals(expected, actual);
    }

    @Test
    public void createField() throws ResourceNotCreatedException, ResourceNotFoundException {
        NewFieldTO field = new NewFieldTO();
        field.setFieldType(FieldTypeEnum.CHECKBOX);
        field.setDescription("New description");
        field.setLabel("New label");

        ExistingFieldTO expected = ExistingFieldTO
                .builder()
                .id(2L)
                .fieldType(FieldTypeEnum.CHECKBOX)
                .description("New description")
                .label("New label")
                .build();

        DBFieldService service = serviceManager.getDbFieldService();

        service.createField(field, 1L);
        List<ExistingFieldTO> existingFieldTOS = service.obtainFieldsByViewId(1L);

        assertEquals(2, existingFieldTOS.size());
        assertEquals(expected, existingFieldTOS.get(1));
    }

    @Test
    public void updateField() throws ResourceNotUpdatedException, ResourceNotFoundException {
        DBFieldService service = serviceManager.getDbFieldService();
        ExistingFieldTO expected = prepareField("Updated text field", FieldTypeEnum.CHECKBOX, "Updated field test description");

        service.updateField(expected, 1L);
        ExistingFieldTO actual = service.obtainFieldsByViewId(1L).get(0);

        assertEquals(expected, actual);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteField() throws ResourceNotDeletedException, ResourceNotFoundException {
        DBFieldService service = serviceManager.getDbFieldService();

        Long id = service.obtainFieldsByViewId(1L).get(0).getId();
        assertEquals(Long.valueOf(1), id);

        service.deleteField(1L);

        service.obtainFieldsByViewId(1L);
    }

    private ExistingFieldTO prepareField(String label, FieldTypeEnum fieldType, String description) {
        return ExistingFieldTO
                .builder()
                .id(1L)
                .label(label)
                .fieldType(fieldType)
                .description(description)
                .build();
    }
}