package com.gmw.services.mod.impl;

import com.gmw.coverters.ModConverter;
import com.gmw.coverters.TOConverter;
import com.gmw.exceptions.SqlRepositoryException;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBModServiceImpl extends DBModReadServiceImpl implements DBModService {
    private static final Logger LOGGER = LogManager.getLogger();

    public DBModServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Long createMod(NewModTO newMod) throws ResourceNotCreatedException {
        Repository<Mod> repository = getRepositoryManager().getModRepository();
        TOConverter<NewModTO, Mod> converter = new ModConverter();

        Mod mod = converter.convertToModel(newMod);

        try {
            return repository.create(mod);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Error during adding new mod!", e);
            throw new ResourceNotCreatedException(e);
        }
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
