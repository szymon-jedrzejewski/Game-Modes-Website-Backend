package com.gmw.persistence.sql;

import com.gmw.persistence.Persistable;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlInjectionChecker {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final List<String> FORBIDDEN_WORDS = new ArrayList<>();

    static {
        FORBIDDEN_WORDS.add("SELECT");
    }

    public static boolean check(Persistable persistable) {
        Field[] fields = persistable.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.getType().equals(String.class)) {
                try {
                    String value = (String) field.get(persistable);

                    for (String separator : FORBIDDEN_WORDS) {
                        if (value.toUpperCase().contains(separator)) {
                            return true;
                        }
                    }

                } catch (IllegalAccessException e) {
                    LOGGER.error("Error during checking for SQL injection!");
                }
            }
        }

        return false;
    }

    public static boolean check(QuerySpec querySpec) {
        for (String forbiddenWord : FORBIDDEN_WORDS) {
            for (Object spec : querySpec.getSpecs()) {
                if (spec instanceof SearchValue
                        && ((SearchValue) spec).clazz().equals(String.class)) {
                    String value = (String)((SearchValue) spec).value();
                    if (value.contains(forbiddenWord))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
