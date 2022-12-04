CREATE DATABASE mods;

CREATE TABLE categories
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(100),
    password VARCHAR(100),
    email    VARCHAR(100),
    role     VARCHAR(10),
    avatar   BYTEA
);

CREATE TABLE games
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(200),
    avatar      BYTEA,
    description TEXT
);

CREATE TABLE views
(
    id      SERIAL PRIMARY KEY,
    game_id INT,
    CONSTRAINT fk_games
        FOREIGN KEY (game_id)
            REFERENCES games (id)
);

CREATE TABLE fields
(
    id          SERIAL PRIMARY KEY,
    view_id     INT,
    name        VARCHAR(200),
    description TEXT,
    type        VARCHAR(50),
    values      TEXT,
    CONSTRAINT FK_views FOREIGN KEY (view_id) REFERENCES views (id)
);

CREATE TABLE mods
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(200),
    user_id       INT,
    game_id       INT,
    category_id   INT,
    description   TEXT,
    download_link TEXT,
    date          DATE,
    avatar        bytea,
    CONSTRAINT FK_games FOREIGN KEY (game_id) REFERENCES games (id),
    CONSTRAINT FK_users FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT FK_category FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE ratings
(
    id          SERIAL PRIMARY KEY,
    user_id     INT,
    mod_id      INT,
    rating VARCHAR(5),
    CONSTRAINT FK_users FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT FK_mods FOREIGN KEY (mod_id) REFERENCES mods (id)
);

CREATE TABLE comments
(
    id      SERIAL PRIMARY KEY,
    user_id INT,
    mod_id  INT,
    comment TEXT,
    CONSTRAINT FK_users FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT FK_mods FOREIGN KEY (mod_id) REFERENCES mods (id)
);