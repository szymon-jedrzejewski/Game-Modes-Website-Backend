package com.gmw.services.view.impl;


import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.Field;
import com.gmw.model.View;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.view.DBViewService;
import com.gmw.view.tos.ExistingFieldTO;
import com.gmw.view.tos.ExistingViewTO;
import com.gmw.view.tos.NewFieldTO;
import com.gmw.view.tos.NewViewTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DBViewServiceImpl extends DBViewReadServiceImpl implements DBViewService {

    private static final Logger LOGGER = LogManager.getLogger();

    public DBViewServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Long createView(NewViewTO view) throws ResourceNotCreatedException {

        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepository();

        View repositoryView = new View("views");
        repositoryView.setGameId(view.getGameId());

        try {
            return viewRepositoryManager.create(repositoryView);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Vie cannot be created!", e);
            throw new ResourceNotCreatedException();
        }
    }

    @Override
    public void deleteView(Long viewId) throws ResourceNotDeletedException {

        Repository<Field> fieldRepositoryManager = getRepositoryManager().getFieldRepository();

        ServiceUtils.delete(fieldRepositoryManager, viewId);
    }
}
