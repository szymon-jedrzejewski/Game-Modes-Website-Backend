package com.gmw.entity;

import jakarta.persistence.*;

@Entity
public class Field {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private FieldTypeEnum fieldType;
    private String values;
    @ManyToOne
    @JoinColumn(name = "view_id")
    private View view;
}
