package com.gmw.model;

import lombok.Getter;

public abstract class Table {

    @Getter
    private final String tableName;

    public Table(String tableName) {
        this.tableName = tableName;
    }
}
