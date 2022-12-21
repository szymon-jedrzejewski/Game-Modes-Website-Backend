package com.gmw.coverters;

import com.gmw.game.tos.ExistingGameTO;
import com.gmw.game.tos.NewGameTO;
import com.gmw.model.Game;

public class GameConverter implements
        ModelConverter<ExistingGameTO, Game>,
        TOConverter<NewGameTO, Game> {

    @Override
    public ExistingGameTO convertToTO(Game game) {
        return ExistingGameTO
                .builder()
                .id(game.getId())
                .name(game.getName())
                .description(game.getDescription())
                .avatar(game.getAvatar())
                .build();
    }

    @Override
    public Game convertToModel(NewGameTO newGameTO) {
        Game game = new Game();
        game.setName(newGameTO.getName());
        game.setDescription(newGameTO.getDescription());
        game.setAvatar(newGameTO.getAvatar());

        return game;
    }
}
