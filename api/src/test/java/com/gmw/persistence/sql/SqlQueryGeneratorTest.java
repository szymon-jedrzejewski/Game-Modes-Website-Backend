package com.gmw.persistence.sql;

import com.gmw.exceptions.SqlQueryGeneratorException;
import com.gmw.model.Field;
import com.gmw.persistence.Persistable;
import com.gmw.viewbuilder.enums.FieldTypeEnum;
import org.junit.Assert;
import org.junit.Test;


public class SqlQueryGeneratorTest {

    @Test
    public void generateFindQuery() {
    }

    @Test
    public void generateCreateQuery() throws SqlQueryGeneratorException {
        Field field = new Field("fields",
                1L,
                "test",
                "some description",
                FieldTypeEnum.TEXT,
                "",
                1L);
        String actualQuery = SqlQueryGenerator.generateCreateQuery(field);
        System.out.print(actualQuery);
        String expectedQuery = "INSERT INTO fields (\"name\",\"description\",\"field_type\",\"values\",\"view_id\") VALUES ('test', 'some description', 'TEXT', '', 1);";
        Assert.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void generateDeleteQuery() {
    }

    @Test
    public void generateUpdateQuery() {
    }

    @Test
    public void resultSetToPersistable() {
    }
}