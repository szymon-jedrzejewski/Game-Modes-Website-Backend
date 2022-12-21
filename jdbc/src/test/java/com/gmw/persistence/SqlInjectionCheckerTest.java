package com.gmw.persistence;

import com.gmw.model.View;
import com.gmw.persistence.sql.SqlInjectionChecker;
import org.junit.Assert;
import org.junit.Test;


public class SqlInjectionCheckerTest {

    @Test
    public void check_SimpleView() {
        View view = new View();
        view.setGameId(1L);
        view.setId(1L);
        Assert.assertFalse(SqlInjectionChecker.check(view));
    }
}