package com.gmw.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class View {

    private Long id;
    private Long gameId;
    private List<Field> fields;

    public View(Long gameId, List<Field> fields) {
        this.gameId = gameId;
        this.fields = fields;
    }
}
