package com.gmw.persistence.sql;

import com.gmw.model.View;
import graphql.Assert;
import org.junit.Test;


public class SqlInjectionCheckerTest {

    @Test
    public void check_SimpleView() {
        View view = new View("views", 1L, 1L);
        Assert.assertFalse(SqlInjectionChecker.check(view));
    }
}