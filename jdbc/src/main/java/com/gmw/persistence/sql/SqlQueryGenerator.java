package com.gmw.persistence.sql;

import com.gmw.exceptions.SqlQueryGeneratorException;
import com.gmw.persistence.Persistable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlQueryGenerator {

    private static final Logger logger = LogManager.getLogger();

    public static String generateCreateQuery(Persistable persistable) throws SqlQueryGeneratorException {
        try {
            String query = """
                    INSERT INTO %s
                    (%s) VALUES (%s);
                    """;
            String tableName = getTableName(persistable);
            String fieldNames = extractFieldNames(persistable);
            String fieldValues = extractFieldValues(persistable);

            return String.format(query, tableName, fieldNames, fieldValues);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("Error during generating create query!");
            throw new SqlQueryGeneratorException("Error during generating create query");
        }

    }

    private static String extractFieldValues(Persistable persistable) {
        Field[] fields = persistable.getClass().getDeclaredFields();
        StringBuilder query = new StringBuilder();

        for (Field field : fields) {
            field.setAccessible(true);
            logger.debug("Field type: " + field.getType());
            if (!field.getName().equals("id")) {
                try {
                    Object object = field.get(persistable);
                    String fieldTypeName = field.getType().getTypeName();
                    logger.debug("Field value: " + object);
                    if (isFieldGivenType(fieldTypeName, "String")) {
                        query.append("'")
                                .append(object)
                                .append("'")
                                .append(", ");
                    } else if (isFieldGivenType(fieldTypeName, "boolean")) {
                        query.append(String.valueOf(object).toUpperCase())
                                .append(", ");
                    } else if (isFieldGivenType(fieldTypeName, "int")) {
                        query.append(object)
                                .append(", ");
                    } else if (isFieldGivenType(fieldTypeName, "Character[]")) {
                        logger.debug("password: " + Arrays.toString((Character[]) object));
                        query.append("'")
                                .append(charArrayToString((Character[]) object))
                                .append("'")
                                .append(", ");
                    }

                } catch (IllegalAccessException e) {
                    logger.error("IllegalAccessException: " + e.getMessage());
                }
            }
        }

        logger.debug("Fields values: " + query);
        return query.deleteCharAt(query.lastIndexOf(",")).toString().trim();
    }

    private static String extractFieldNames(Persistable persistable) {
        Field[] fields = persistable.getClass().getDeclaredFields();
        List<String> fieldsNames = new ArrayList<>();

        for (Field field : fields) {
            if (!field.getName().equals("id")) {
                field.setAccessible(true);
                fieldsNames.add(field.getName());
            }
        }

        return String.join(",", fieldsNames);
    }

    private static String getTableName(Persistable persistable) throws NoSuchFieldException, IllegalAccessException {
        Field field = persistable.getClass().getSuperclass().getDeclaredField("tableName");
        field.setAccessible(true);
        return (String) field.get(persistable);
    }

    private static boolean isFieldGivenType(String fieldType, String type) {
        return fieldType.toLowerCase().contains(type.toLowerCase());
    }

    private static String charArrayToString(Character[] characters) {
        StringBuilder result = new StringBuilder();
        for (Character character : characters) {
            result.append(character);
        }
        return result.toString();
    }
}
