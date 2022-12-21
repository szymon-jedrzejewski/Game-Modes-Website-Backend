package com.gmw.services.testutilities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDbConstants {
    private static final Map<ServiceType, List<String>> serviceTypeToQuery = new HashMap<>();

    private static final String DROP_USERS = "\nDROP TABLE IF EXISTS users CASCADE;\n";

    private static final String DROP_CATEGORIES = "\nDROP TABLE IF EXISTS categories CASCADE;\n";
    private static final String DROP_GAMES = "\nDROP TABLE IF EXISTS games CASCADE;\n";
    private static final String DROP_VIEWS = "\nDROP TABLE IF EXISTS views CASCADE;\n";
    private static final String DROP_FIELDS = "\nDROP TABLE IF EXISTS fields CASCADE;\n";
    private static final String DROP_RATINGS = "\nDROP TABLE IF EXISTS ratings CASCADE;\n";
    private static final String DROP_COMMENTS = "\nDROP TABLE IF EXISTS comments CASCADE;\n";
    private static final String DROP_MODS = "\nDROP TABLE IF EXISTS mods CASCADE;\n";
    private static final String DROP_FIELDS_VALUES = "\nDROP TABLE IF EXISTS fields_values CASCADE;\n";
    private static final String CREATE_CATEGORIES = """
            CREATE TABLE categories
            (
                id   SERIAL PRIMARY KEY,
                name VARCHAR(100)
            );
                        
            """;
    private static final String CREATE_USERS = """
                
                    CREATE TABLE users
                    (
                        id       SERIAL PRIMARY KEY,
                        name     VARCHAR(100),
                        password VARCHAR(100),
                        email    VARCHAR(100),
                        role     VARCHAR(10),
                        avatar   BYTEA
                    );
                        
            """;
    private static final String CREATE_GAMES = """ 
                        
            CREATE TABLE games
            (
                id          SERIAL PRIMARY KEY,
                name        VARCHAR(200),
                avatar      BYTEA,
                description VARCHAR(200)
            );
                        
            """;
    private static final String CREATE_VIEWS = """ 
                        
            CREATE TABLE views
            (
                id      SERIAL PRIMARY KEY,
                game_id INT,
                CONSTRAINT fk_view_games
                    FOREIGN KEY (game_id)
                        REFERENCES games (id)
            );
                        
            """;
    private static final String CREATE_FIELDS = """
                        
            CREATE TABLE fields
            (
                id          SERIAL PRIMARY KEY,
                view_id     INT,
                description VARCHAR(200),
                type        VARCHAR(50),
                label       VARCHAR(200),
                CONSTRAINT FK_views FOREIGN KEY (view_id) REFERENCES views (id)
            );
                        
            """;
    private static final String CREATE_MODS = """ 
                        
            CREATE TABLE mods
            (
                id            SERIAL PRIMARY KEY,
                name          VARCHAR(200),
                user_id       INT,
                game_id       INT,
                category_id   INT,
                description   VARCHAR(200),
                download_link VARCHAR(200),
                date          DATE,
                avatar        bytea,
                CONSTRAINT FK_games FOREIGN KEY (game_id) REFERENCES games (id),
                CONSTRAINT FK_mods_users FOREIGN KEY (user_id) REFERENCES users (id),
                CONSTRAINT FK_category FOREIGN KEY (category_id) REFERENCES categories (id)
            );
                        
            """;
    private static final String CREATE_RATINGS = """
                        
            CREATE TABLE ratings
            (
                id      SERIAL PRIMARY KEY,
                user_id INT,
                mod_id  INT,
                rating  INT,
                CONSTRAINT FK_ratings_users FOREIGN KEY (user_id) REFERENCES users (id),
                CONSTRAINT FK_ratings_mods FOREIGN KEY (mod_id) REFERENCES mods (id)
            );
                        
            """;
    private static final String CREATE_COMMENTS = """
                        
            CREATE TABLE comments
            (
                id      SERIAL PRIMARY KEY,
                user_id INT,
                mod_id  INT,
                comment VARCHAR(255),
                CONSTRAINT FK_comments_users FOREIGN KEY (user_id) REFERENCES users (id),
                CONSTRAINT FK_comments_mods FOREIGN KEY (mod_id) REFERENCES mods (id)
            );
                        
            """;
    private static final String CREATE_FIELDS_VALUES = """
                        
            CREATE TABLE fields_values
            (
                id          SERIAL PRIMARY KEY,
                field_id    INT,
                value       VARCHAR(255),
                CONSTRAINT FK_fields FOREIGN KEY (field_id) REFERENCES fields (id)
            );
                        
            """;
    private static final String INSERT_USER = "INSERT INTO users VALUES(1, 'test_name', 'secret_password', 'email@org.com', 'USER', null);";
    private static final String INSERT_CATEGORY = "INSERT INTO categories VALUES(1, 'category_test_name');";
    private static final String INSERT_GAME = "INSERT INTO games VALUES(1, 'game_test_name', null, 'Game description test');";
    private static final String INSERT_VIEW = "INSERT INTO views VALUES(1, 1);";
    private static final String INSERT_FIELD = "INSERT INTO fields VALUES(1, 1, 'Field test description', 'TEXT', 'Text field');";
    private static final String INSERT_MOD = "INSERT INTO mods VALUES(1, 'TestModName', 1, 1, 1, 'Test description mods', 'www.google.com', '2001-09-28', null);";
    private static final String INSERT_RATING = "INSERT INTO ratings VALUES(1, 1, 1, 5);";
    private static final String INSERT_COMMENT = "INSERT INTO comments VALUES(1, 1, 1, 'That mod is awesome test');";
    private static final String INSERT_FIELDS_VALUES = "INSERT INTO field_values VALUES(1, 1, 'Test field value');";


    static {
        serviceTypeToQuery.put(ServiceType.USER, new LinkedList<>(List.of(DROP_USERS, CREATE_USERS, INSERT_USER)));

        serviceTypeToQuery.put(ServiceType.CATEGORY, new LinkedList<>(List.of(DROP_CATEGORIES, CREATE_CATEGORIES,
                INSERT_CATEGORY)));

        serviceTypeToQuery.put(ServiceType.GAME, new LinkedList<>(List.of(DROP_GAMES, CREATE_GAMES, INSERT_GAME)));

        serviceTypeToQuery.put(ServiceType.MOD, new LinkedList<>(List.of(DROP_USERS, DROP_GAMES, DROP_CATEGORIES,
                DROP_MODS, CREATE_USERS, INSERT_USER, CREATE_GAMES, INSERT_GAME, CREATE_CATEGORIES, INSERT_CATEGORY,
                CREATE_MODS, INSERT_MOD)));

        serviceTypeToQuery.put(ServiceType.FIELD, new LinkedList<>(List.of(DROP_VIEWS, DROP_GAMES, CREATE_GAMES,
                INSERT_GAME, CREATE_VIEWS, INSERT_VIEW, DROP_FIELDS, CREATE_FIELDS, INSERT_FIELD)));

        serviceTypeToQuery.put(ServiceType.RATING, new LinkedList<>(List.of(DROP_USERS, DROP_GAMES, DROP_CATEGORIES,
                DROP_MODS, CREATE_USERS, INSERT_USER, CREATE_GAMES, INSERT_GAME, CREATE_CATEGORIES, INSERT_CATEGORY,
                CREATE_MODS, INSERT_MOD, DROP_RATINGS, CREATE_RATINGS, INSERT_RATING)));

        serviceTypeToQuery.put(ServiceType.COMMENT, new LinkedList<>(List.of(DROP_USERS, DROP_GAMES, DROP_CATEGORIES,
                DROP_MODS, CREATE_USERS, INSERT_USER, CREATE_GAMES, INSERT_GAME, CREATE_CATEGORIES, INSERT_CATEGORY,
                CREATE_MODS, INSERT_MOD, DROP_COMMENTS, CREATE_COMMENTS, INSERT_COMMENT)));

        serviceTypeToQuery.put(ServiceType.VIEW, new LinkedList<>(List.of(DROP_VIEWS, DROP_GAMES, CREATE_GAMES,
                INSERT_GAME, CREATE_VIEWS, INSERT_VIEW)));

        serviceTypeToQuery.put(ServiceType.FIELD_VALUE, new LinkedList<>(List.of(DROP_VIEWS, DROP_GAMES, CREATE_GAMES,
                INSERT_GAME, CREATE_VIEWS, INSERT_VIEW, DROP_FIELDS, CREATE_FIELDS, INSERT_FIELD, DROP_FIELDS_VALUES,
                CREATE_FIELDS_VALUES, INSERT_FIELDS_VALUES)));
    }

    public static List<String> getQueries(ServiceType serviceType) {
        return serviceTypeToQuery.get(serviceType);
    }
}
