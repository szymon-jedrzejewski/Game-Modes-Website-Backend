package com.gmw.persistence;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class QuerySpec {
    private static final Logger logger = LogManager.getLogger();
    @Getter
    @Setter
    private String tableName;
    @Getter
    @Setter
    private Class<?> clazz;
    @Getter
    private final List<Object> specs = new ArrayList<>();

    public QuerySpec() {
    }

    public QuerySpec(String tableName) {
        this.tableName = tableName;
    }

    public void append(QueryOperator queryOperator, SearchCondition searchCondition) {

        specs.add(queryOperator.toString());
        specs.add(searchCondition.column());
        specs.add(searchCondition.operator().toString());
        specs.add(new SearchValue(searchCondition.value().getClass(), searchCondition.value()));

        logger.debug("Specs: " + specs);
    }
}
