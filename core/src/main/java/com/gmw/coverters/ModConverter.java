package com.gmw.coverters;

import com.gmw.mod.tos.ExistingModTO;
import com.gmw.mod.tos.NewModTO;
import com.gmw.model.Mod;

public class ModConverter implements
        ModelConverter<ExistingModTO, Mod>,
        TOConverter<NewModTO, Mod> {

    @Override
    public ExistingModTO convertToTO(Mod mod) {
        return ExistingModTO
                .builder()
                .id(mod.getId())
                .avatar(mod.getAvatar())
                .description(mod.getDescription())
                .categoryId(mod.getCategoryId())
                .date(mod.getDate())
                .userId(mod.getUserId())
                .downloadLink(mod.getDownloadLink())
                .gameId(mod.getGameId())
                .name(mod.getName())
                .build();
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
