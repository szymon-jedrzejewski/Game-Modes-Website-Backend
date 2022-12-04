package com.gmw.mod.tos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExistingMod extends NewMod {
    private Long id;

    @Builder
    public ExistingMod(String name,
                       Long userId,
                       Long gameId,
                       Long categoryId,
                       String description,
                       String downloadLink,
                       Date date,
                       byte[] avatar,
                       Long id) {
        super(name, userId, gameId, categoryId, description, downloadLink, date, avatar);
        this.id = id;
    }
}
