package com.gmw.persistence.sql;

import com.gmw.exceptions.SqlQueryUtilityException;
import com.gmw.model.Field;
import com.gmw.view.enums.FieldTypeEnum;
import org.junit.Assert;
import org.junit.Test;


public class SqlQueryUtilityTest {

    @Test
    public void generateFindQuery() {
    }

    @Test
    public void generateCreateQuery() throws SqlQueryUtilityException {
        Field field = prepareFieldObject();
        String actualQuery = SqlQueryUtility.generateCreateQuery(field);
        System.out.print(actualQuery);
        String expectedQuery = "INSERT INTO fields (\"name\",\"description\",\"field_type\",\"values\",\"view_id\") VALUES ('test', 'some description', 'TEXT', '', 1);";
        Assert.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void generateDeleteQuery() {
        String actual = SqlQueryUtility.generateDeleteQuery("views", 1);
        String expected = "DELETE FROM views WHERE id = 1;";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void generateUpdateQuery() throws SqlQueryUtilityException {
        String actual = SqlQueryUtility.generateUpdateQuery(prepareFieldObject());
        String expected = "UPDATE fields SET " +
                "name = 'test', " +
                "description = 'some description', " +
                "field_type = 'TEXT', " +
                "values = '', " +
                "view_id = 1 WHERE id = 1;";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void resultSetToPersistable() {
    }

    private Field prepareFieldObject() {
        return new Field("fields",
                1L,
                "test",
                "some description",
                FieldTypeEnum.TEXT.toString(),
                "",
                1L);
    }
}