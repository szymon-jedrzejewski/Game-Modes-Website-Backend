package com.gmw.persistence;

import java.util.List;

public record SearchCondition(String column, Operator operator, List<Object> values) {
}
