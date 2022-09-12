package com.gmw.persistence;

public record SearchValue(Class<?> clazz, Object value) {
    @Override
    public String toString() {
        return "SearchValue{" +
                "clazz=" + clazz +
                ", value=" + value +
                '}';
    }
}
