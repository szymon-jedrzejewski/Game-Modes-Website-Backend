package com.gmw.persistence;

import java.util.List;

public record SearchValue(Class<?> clazz, List<Object> values) {
    @Override
    public String toString() {
        return "SearchValue{" +
                "clazz=" + clazz +
                ", value=" + values +
                '}';
    }
}
