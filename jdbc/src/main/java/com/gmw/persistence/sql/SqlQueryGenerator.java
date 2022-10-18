package com.gmw.persistence.sql;

import com.gmw.exceptions.SqlQueryGeneratorException;
import com.gmw.persistence.Persistable;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlQueryGenerator {

    private static final Logger logger = LogManager.getLogger();

    public static String generateFindQuery(QuerySpec querySpec) {
        String tableName = querySpec.getTableName();
        if (querySpec.getSpecs() == null || querySpec.getSpecs().isEmpty()) {
            logger.debug("Find query: " + "SELECT * FROM " + tableName + ";");
            return "SELECT * FROM " + tableName + "s;";
        }

        logger.debug("SELECT * FROM " + tableName + " " + querySpecToSql(querySpec) + ";");
        return "SELECT * FROM " + tableName + " " + querySpecToSql(querySpec) + ";";
    }

    public static String generateCreateQuery(Persistable persistable) throws SqlQueryGeneratorException {
        try {
            String query = "INSERT INTO %s (%s) VALUES (%s);";
            String tableName = getTableName(persistable);
            String fieldNames = extractFieldNames(persistable);
            String fieldValues = extractFieldValues(persistable);

            return String.format(query, tableName, fieldNames, fieldValues);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("Error during generating create query!");
            throw new SqlQueryGeneratorException("Error during generating create query");
        }
    }

    public static String generateDeleteQuery(String tableName, int id) {
        logger.debug("Delete Query: DELETE FROM " + tableName + "s WHERE id=" + id);
        return "DELETE FROM " + tableName + " WHERE id = " + id + ";";
    }

    public static String generateUpdateQuery(Persistable persistable) throws SqlQueryGeneratorException {
        try {
            Field[] fields = persistable.getClass().getDeclaredFields();
            List<String> params = new ArrayList<>();
            String id = "";

            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = toSnakeCase(field.getName());
                Object value = field.get(persistable);
                if (fieldName.equals("id")) {
                    id = String.valueOf(value);
                }
                String fieldTypeName = field.getType().getTypeName();

                if (!fieldName.equals("id") && !fieldName.equals("creator")) {
                    if (isFieldGivenType(fieldTypeName, "String")) {
                        params.add(fieldName + " = '" + value + "'");
                    } else if (isFieldGivenType(fieldTypeName, "int")) {
                        params.add(fieldName + " = " + value);
                    } else if (isFieldGivenType(fieldTypeName, "long")) {
                        params.add(fieldName + " = " + value);
                    } else if (isFieldGivenType(fieldTypeName, "double")) {
                        params.add(fieldName + " = " + value);
                    } else if (isFieldGivenType(fieldTypeName, "boolean")) {
                        params.add(fieldName + " = " + String.valueOf(value).toUpperCase());
                    } else {
                        params.add(fieldName + " = '" + value + "'");
                    }
                }
            }

            logger.debug("Update query: " + "UPDATE " + getTableName(persistable)
                    + "SET " + String.join(", ", params)
                    + " WHERE id=" + id + ";");

            return "UPDATE " + getTableName(persistable)
                    + " SET " + String.join(", ", params)
                    + " WHERE id = " + id + ";";
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        throw new SqlQueryGeneratorException();
    }

    public static List<Persistable> resultSetToPersistable(PreparedStatement preparedStatement, QuerySpec querySpec)
            throws NoSuchMethodException, SQLException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        List<Persistable> persistables = new ArrayList<>();
        Constructor<?> constructor = querySpec.getClazz().getDeclaredConstructor();
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {

            Persistable persistable = (Persistable) constructor.newInstance();
            logger.debug("Created object: " + persistable);
            Field[] fields = persistable.getClass().getDeclaredFields();

            for (Field field : fields) {

                field.setAccessible(true);
                logger.debug("Column name: " + field.getName());
                logger.debug("Value: " + result.getObject(field.getName()));
                field.set(persistable, result.getObject(field.getName()));
            }

            persistables.add(persistable);
        }
        return persistables;
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
                    } else if (isFieldGivenType(fieldTypeName, "long")) {
                        query.append(object)
                                .append(", ");
                    } else if (isFieldGivenType(fieldTypeName, "Character[]")) {
                        logger.debug("password: " + Arrays.toString((Character[]) object));
                        query.append("'")
                                .append(charArrayToString((Character[]) object))
                                .append("'")
                                .append(", ");
                    } else {
                        query.append("'")
                                .append(object)
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
            String fieldName = field.getName();
            if (!fieldName.equals("id")) {
                field.setAccessible(true);
                fieldsNames.add("\"" + toSnakeCase(fieldName) + "\"");
            }
        }

        return String.join(",", fieldsNames);
    }

    private static String toSnakeCase(String fieldName) {
        char[] originalFieldName = fieldName.toCharArray();
        List<Character> snakeCaseFieldNames = new ArrayList<>();

        for (char ch : originalFieldName) {
            if (Character.isUpperCase(ch)) {
                snakeCaseFieldNames.add('_');
                snakeCaseFieldNames.add(Character.toLowerCase(ch));
            } else {
                snakeCaseFieldNames.add(ch);
            }
        }

        return snakeCaseFieldNames
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining());
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

    private static String querySpecToSql(QuerySpec querySpec) {
        List<Object> specs = querySpec.getSpecs();
        List<String> values = new ArrayList<>();
        for (Object spec : specs) {
            if (spec instanceof SearchValue) {
                if (((SearchValue) spec).clazz().equals(String.class)) {
                    values.add("'" + ((SearchValue) spec).value() + "'");
                } else {
                    values.add(((SearchValue) spec).value().toString());
                }
            } else {
                values.add(spec.toString());
            }
        }
        logger.debug("QuerySpecToSql list: " + specs);
        return String.join(" ", values);
    }
}
