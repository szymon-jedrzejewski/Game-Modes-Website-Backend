package com.gmw.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class View {

    @Id
    @GeneratedValue
    private Long id;
    private Long gameId;
    @OneToMany(mappedBy = "view")
    private List<Field> fields;

    public View() {
    }

    public View(Long id, Long gameId, List<Field> fields) {
        this.id = id;
        this.gameId = gameId;
        this.fields = fields;
    }

    public View(Long gameId, List<Field> fields) {
        this.gameId = gameId;
        this.fields = fields;
    }

    public Long getId() {
        return id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
