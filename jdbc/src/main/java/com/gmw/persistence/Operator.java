package com.gmw.persistence;

public enum Operator {

    EQUAL_TO("="), GREATER_THAN(">"), LESS_THAN("<"), GREATER_OR_EQUAL(">="), LESS_OR_EQUAL("<="), NOT_EQUAL("!="), IN("IN");

    private final String operator;

    Operator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator;
    }
}
