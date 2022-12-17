package com.gmw.coverters;

import com.gmw.model.View;
import com.gmw.view.tos.ExistingViewTO;
import com.gmw.view.tos.NewViewTO;

public class ViewConverter implements
        ModelConverter<ExistingViewTO, View>,
        TOConverter<NewViewTO, View> {

    @Override
    public ExistingViewTO convertToTO(View view) {
        return ExistingViewTO
                .builder()
                .id(view.getId())
                .gameId(view.getGameId())
                .build();
    }

    @Override
    public View convertToModel(NewViewTO existingViewTO) {
        View view = new View("views");
        view.setGameId(existingViewTO.getGameId());
        return view;
    }
}
