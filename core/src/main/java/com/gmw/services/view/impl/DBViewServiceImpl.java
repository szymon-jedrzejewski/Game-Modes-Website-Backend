package com.gmw.services.view.impl;


import com.gmw.coverters.TOConverter;
import com.gmw.coverters.ViewConverter;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.View;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.view.DBViewService;
import com.gmw.view.tos.NewViewTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBViewServiceImpl extends DBViewReadServiceImpl implements DBViewService {

    private static final Logger LOGGER = LogManager.getLogger();

    public DBViewServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Long createView(NewViewTO newView) throws ResourceNotCreatedException {

        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepository();

        TOConverter<NewViewTO, View> converter = new ViewConverter();
        View view = converter.convertToModel(newView);

        try {
            return viewRepositoryManager.create(view);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Vie cannot be created!", e);
            throw new ResourceNotCreatedException();
        }
    }

    @Override
    public void deleteView(Long viewId) throws ResourceNotDeletedException {

        Repository<View> repository = getRepositoryManager().getViewRepository();

        ServiceUtils.delete(repository, viewId);
    }
}
