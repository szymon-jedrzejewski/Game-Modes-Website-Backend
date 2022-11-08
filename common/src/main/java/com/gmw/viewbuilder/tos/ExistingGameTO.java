package com.gmw.viewbuilder.tos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExistingGameTO extends NewGameTO {

    private final Long id;

    @Builder
    public ExistingGameTO(String name, byte[] avatar, String description, Long id) {
        super(name, avatar, description);
        this.id = id;
    }
}
