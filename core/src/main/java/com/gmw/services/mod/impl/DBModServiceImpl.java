package com.gmw.services.mod.impl;

import com.gmw.coverters.ModConverter;
import com.gmw.coverters.TOConverter;
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
        TOConverter<NewModTO, Mod> converter = new ModConverter();

        Mod mod = converter.convertToModel(newMod);

        ServiceUtils.create(repository, mod);
    }

    @Override
    public void deleteMod(Long modId) throws ResourceNotDeletedException {
        Repository<Mod> repository = getRepositoryManager().getModRepository();
        ServiceUtils.delete(repository, modId);
    }

    @Override
    public void updateMod(ExistingModTO existingModTO) throws ResourceNotUpdatedException {
        Repository<Mod> repository = getRepositoryManager().getModRepository();

        Mod mod = new Mod();
        mod.setId(existingModTO.getId());
        mod.setAvatar(existingModTO.getAvatar());
        mod.setDate(existingModTO.getDate());
        mod.setDescription(existingModTO.getDescription());
        mod.setGameId(existingModTO.getGameId());
        mod.setUserId(existingModTO.getUserId());
        mod.setDownloadLink(existingModTO.getDownloadLink());
        mod.setCategoryId(existingModTO.getCategoryId());
        mod.setName(existingModTO.getName());

        ServiceUtils.update(repository, mod);
    }
}
