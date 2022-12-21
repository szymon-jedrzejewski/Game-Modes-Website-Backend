package com.gmw.model;

import com.gmw.persistence.Persistable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Rating extends Table implements Persistable {
    private Long id;
    private Long userId;
    private Long modId;
    private Integer rating;


    public Rating() {
        super("ratings");
    }
}
