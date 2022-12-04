package com.gmw.game.tos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExistingGameTO extends NewGameTO {

    private Long id;

    @Builder
    public ExistingGameTO(String name, byte[] avatar, String description, Long id) {
        super(name, avatar, description);
        this.id = id;
    }
}
