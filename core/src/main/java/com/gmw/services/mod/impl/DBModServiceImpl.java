package com.gmw.services.mod.impl;

import com.gmw.mod.tos.ExistingModTO;
import com.gmw.mod.tos.NewModTO;
import com.gmw.model.Mod;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.mod.DBModService;

public class DBModServiceImpl extends DBModReadServiceImpl implements DBModService {
    public DBModServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createMod(NewModTO newMod) throws ResourceNotCreatedException {
        Repository<Mod> repository = getRepositoryManager().getModRepository();

        Mod mod = prepareMod(newMod);

        ServiceUtils.create(repository, mod);
    }

    private static Mod prepareMod(NewModTO newMod) {
        Mod mod = new Mod("mods");
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

    @Override
    public void deleteMod(Long modId) throws ResourceNotDeletedException {
        Repository<Mod> repository = getRepositoryManager().getModRepository();
        ServiceUtils.delete(repository, modId);
    }

    @Override
    public void updateMod(ExistingModTO existingModTO) throws ResourceNotUpdatedException {
        Repository<Mod> repository = getRepositoryManager().getModRepository();

        Mod mod = prepareMod(existingModTO);
        mod.setId(existingModTO.getId());

        ServiceUtils.update(repository, mod);
    }
}
