package com.gmw.persistence;

public record SearchCondition(String column, Operator operator, Object value) {
}
