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

    public void append(QueryOperator queryOperator, SearchCondition searchCondition) {

        prepareBasicSpecs(queryOperator, searchCondition);

        logger.debug("Specs: " + specs);
    }

    public void appendWithOpeningRoundBracket(QueryOperator queryOperator, SearchCondition searchCondition) {

        List<Object> values = searchCondition.values();
        if (values != null && !values.isEmpty())
        {
            specs.add(queryOperator.toString());
            specs.add("(");
            specs.add(searchCondition.column());
            specs.add(searchCondition.operator().toString());
            specs.add(new SearchValue(values.get(0).getClass(), values));
        }

        logger.debug("Specs: " + specs);
    }

    public void appendWithClosingRoundBracket(QueryOperator queryOperator, SearchCondition searchCondition) {

        prepareBasicSpecs(queryOperator, searchCondition);
        specs.add(")");

        logger.debug("Specs: " + specs);
    }

    private void prepareBasicSpecs(QueryOperator queryOperator, SearchCondition searchCondition) {
        List<Object> values = searchCondition.values();
        if (values != null && !values.isEmpty()) {
            specs.add(queryOperator.toString());
            specs.add(searchCondition.column());
            specs.add(searchCondition.operator().toString());
            specs.add(new SearchValue(values.get(0).getClass(), values));
        }
    }

    @Override
    public String toString() {
        return "QuerySpec{" +
                "tableName='" + tableName + '\'' +
                ", clazz=" + clazz +
                ", specs=" + specs +
                '}';
    }
}
