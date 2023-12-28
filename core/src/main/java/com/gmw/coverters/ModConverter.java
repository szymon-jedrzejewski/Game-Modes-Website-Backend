package com.gmw.coverters;

import com.gmw.mod.tos.ExistingModTO;
import com.gmw.mod.tos.NewModTO;
import com.gmw.model.Mod;

public class ModConverter implements
        ModelConverter<ExistingModTO, Mod>,
        TOConverter<NewModTO, Mod> {

    @Override
    public ExistingModTO convertToTO(Mod mod) {

        return new ExistingModTO(mod.getId(),
                mod.getName(),
                mod.getUserId(),
                mod.getGameId(),
                mod.getCategoryId(),
                mod.getDescription(),
                mod.getDownloadLink(),
                mod.getDate(),
                mod.getAvatar());
    }

    @Override
    public Mod convertToModel(NewModTO newMod) {
        Mod mod = new Mod();
        mod.setAvatar(newMod.getAvatar());
        mod.setDate(newMod.getDate());
        mod.setUserId(newMod.getUserId());
        mod.setDescription(newMod.getDescription());
        mod.setCategoryId(newMod.getCategoryId());
        mod.setDownloadLink(newMod.getDownloadLink());
        mod.setGameId(newMod.getGameId());
        mod.setName(newMod.getName());
        return mod;
    }
}
