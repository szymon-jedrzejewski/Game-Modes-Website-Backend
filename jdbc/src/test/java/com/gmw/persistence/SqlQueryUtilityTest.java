package com.gmw.persistence;

import com.gmw.exceptions.SqlQueryUtilityException;
import com.gmw.model.Field;
import com.gmw.model.Game;
import com.gmw.model.User;
import com.gmw.model.View;
import com.gmw.persistence.sql.SqlQueryUtility;
import com.gmw.user.enums.RoleEnum;
import field.enums.FieldTypeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class SqlQueryUtilityTest {
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet resultSet;


    @Test
    public void generateFindByIdQueryGame() {
        QuerySpec querySpec = new QuerySpec();
        querySpec.setClazz(Game.class);
        querySpec.setTableName("games");
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, 1));

        String actual = SqlQueryUtility.generateFindQuery(querySpec);
        String expected = "SELECT * FROM games WHERE id = 1;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void generateFindByNameQueryGame() {
        QuerySpec querySpec = new QuerySpec();
        querySpec.setClazz(Game.class);
        querySpec.setTableName("games");
        querySpec.append(QueryOperator.WHERE, new SearchCondition("name", Operator.EQUAL_TO, "Fifa"));

        String actual = SqlQueryUtility.generateFindQuery(querySpec);
        String expected = "SELECT * FROM games WHERE name = 'Fifa';";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void generateFindByIdQueryView() {
        QuerySpec querySpec = new QuerySpec();
        querySpec.setClazz(View.class);
        querySpec.setTableName("views");
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, 1L));

        String actual = SqlQueryUtility.generateFindQuery(querySpec);
        String expected = "SELECT * FROM views WHERE id = 1;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void generateFindByViewIdQueryField() {
        QuerySpec querySpec = new QuerySpec();
        querySpec.setClazz(Field.class);
        querySpec.setTableName("fields");
        querySpec.append(QueryOperator.WHERE, new SearchCondition("view_id", Operator.EQUAL_TO, 1L));

        String actual = SqlQueryUtility.generateFindQuery(querySpec);
        String expected = "SELECT * FROM fields WHERE view_id = 1;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void generateCreateQueryForField() throws SqlQueryUtilityException {
        Field field = prepareField();
        String actualQuery = SqlQueryUtility.generateCreateQuery(field);
        String expectedQuery = "INSERT INTO fields (description,type,label,view_id) " +
                "VALUES ('some description', 'TEXT', '', 1);";
        Assert.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void generateCreateQueryForGame() throws SqlQueryUtilityException {
        Game game = prepareGame();

        String actualQuery = SqlQueryUtility.generateCreateQuery(game);
        String expectedQuery = "INSERT INTO games (name,description,avatar) " +
                "VALUES ('Name', 'Some desc', null);";
        Assert.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void generateCreateQueryForView() throws SqlQueryUtilityException {
        View view = prepareView();

        String actualQuery = SqlQueryUtility.generateCreateQuery(view);
        String expectedQuery = "INSERT INTO views (game_id) " +
                "VALUES (2);";
        Assert.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void generateDeleteQueryView() {
        String actual = SqlQueryUtility.generateDeleteQuery("views", 1);
        String expected = "DELETE FROM views WHERE id = 1;";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void generateDeleteQueryGame() {
        String actual = SqlQueryUtility.generateDeleteQuery("games", 1);
        String expected = "DELETE FROM games WHERE id = 1;";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void generateUpdateQueryField() throws SqlQueryUtilityException {
        String actual = SqlQueryUtility.generateUpdateQuery(prepareField());
        String expected = "UPDATE fields SET " +
                "description = 'some description', " +
                "type = 'TEXT', " +
                "label = '', " +
                "view_id = 1 WHERE id = 1;";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void generateUpdateQueryGame() throws SqlQueryUtilityException {
        String actual = SqlQueryUtility.generateUpdateQuery(prepareGame());
        String expected = "UPDATE games SET " +
                "name = 'Name', " +
                "description = 'Some desc', " +
                "avatar = null WHERE id = 1;";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void generateUpdateQueryView() throws SqlQueryUtilityException {
        String actual = SqlQueryUtility.generateUpdateQuery(prepareView());
        String expected = "UPDATE views SET " +
                "game_id = 2 " +
                "WHERE id = 1;";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void generateUpdateQueryToUpdateRoleForAUser() throws SqlQueryUtilityException {
        User user = new User();
        user.setId(1L);
        user.setEmail("name@email.com");
        user.setAvatar(null);
        user.setPassword("password");
        user.setName("name");
        user.setRole(RoleEnum.USER.toString());

        String actual = SqlQueryUtility.generateUpdateQuery(user);
        String expected = "UPDATE users SET " +
                "name = 'name', " +
                "password = 'password', " +
                "email = 'name@email.com', " +
                "role = 'USER', " +
                "avatar = null " +
                "WHERE id = 1;";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void resultSetToPersistableGame() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Game expected = prepareGame();

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getObject("id")).thenReturn(1);
        Mockito.when(resultSet.getObject("description")).thenReturn("Some desc");
        Mockito.when(resultSet.getObject("name")).thenReturn("Name");
        Mockito.when(resultSet.getObject("avatar")).thenReturn(null);
        Mockito.when(ps.executeQuery()).thenReturn(resultSet);

        QuerySpec querySpec = new QuerySpec();
        querySpec.setClazz(Game.class);

        Persistable actual = SqlQueryUtility.resultSetToPersistable(ps, querySpec).get(0);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void resultSetToPersistableView() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        View expected = prepareView();

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getObject("id")).thenReturn(1);
        Mockito.when(resultSet.getObject("game_id")).thenReturn(2);
        Mockito.when(ps.executeQuery()).thenReturn(resultSet);

        QuerySpec querySpec = new QuerySpec();
        querySpec.setClazz(View.class);

        Persistable actual = SqlQueryUtility.resultSetToPersistable(ps, querySpec).get(0);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void resultSetToPersistableField() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Field expected = new Field();
        expected.setId(1L);
        expected.setViewId(1L);
        expected.setDescription("Desc");
        expected.setType("TEXT");
        expected.setLabel("valueOne");

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getObject("id")).thenReturn(1);
        Mockito.when(resultSet.getObject("view_id")).thenReturn(1);
        Mockito.when(resultSet.getObject("description")).thenReturn("Desc");
        Mockito.when(resultSet.getObject("type")).thenReturn("TEXT");
        Mockito.when(resultSet.getObject("label")).thenReturn("valueOne");
        Mockito.when(ps.executeQuery()).thenReturn(resultSet);

        QuerySpec querySpec = new QuerySpec();
        querySpec.setClazz(Field.class);

        Persistable actual = SqlQueryUtility.resultSetToPersistable(ps, querySpec).get(0);
        Assert.assertEquals(expected, actual);
    }

    private Game prepareGame() {
        Game game = new Game();
        game.setId(1L);
        game.setDescription("Some desc");
        game.setName("Name");
        game.setAvatar(null);
        return game;
    }

    private Field prepareField() {
        Field field = new Field();
        field.setId(1L);
        field.setDescription("some description");
        field.setType(FieldTypeEnum.TEXT.toString());
        field.setLabel("");
        field.setViewId(1L);
        return field;
    }

    private View prepareView() {
        View view = new View();
        view.setGameId(2L);
        view.setId(1L);
        return view;
    }
}