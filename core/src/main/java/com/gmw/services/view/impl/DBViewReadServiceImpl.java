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
import com.gmw.services.DTOConverter;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.view.DBViewReadService;
import com.gmw.services.DBService;
import com.gmw.view.enums.FieldTypeEnum;
import com.gmw.view.tos.ExistingFieldTO;
import com.gmw.view.tos.ExistingViewTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class DBViewReadServiceImpl extends DBService implements DBViewReadService, DTOConverter<ExistingViewTO, View> {

    private static final Logger LOGGER = LogManager.getLogger();

    public DBViewReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public ExistingViewTO obtainViewById(Long viewId) throws ResourceNotFoundException {
        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepository();
        QuerySpec querySpec = new QuerySpec();
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, viewId));
        querySpec.setClazz(View.class);
        querySpec.setTableName("views");

        return ServiceUtils.find(viewRepositoryManager, this, querySpec).get(0);
    }

    @Override
    public ExistingViewTO obtainViewByGameId(Long gameId) throws ResourceNotFoundException {
        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepository();
        QuerySpec querySpec = new QuerySpec();
        querySpec.append(QueryOperator.WHERE, new SearchCondition("game_id", Operator.EQUAL_TO, gameId));
        querySpec.setClazz(View.class);
        querySpec.setTableName("views");

        return ServiceUtils.find(viewRepositoryManager, this, querySpec).get(0);
    }

    @Override
    public ExistingViewTO convert(View view) {
        try {
            QuerySpec querySpec = new QuerySpec();
            querySpec.append(QueryOperator.WHERE, new SearchCondition("view_id", Operator.EQUAL_TO, view.getId()));
            querySpec.setTableName("fields");
            querySpec.setClazz(Field.class);

            List<ExistingFieldTO> existingFieldTOS = getRepositoryManager()
                    .getFieldRepository()
                    .find(querySpec)
                    .stream()
                    .map(this::mapToExistingField).toList();

            return ExistingViewTO
                    .builder()
                    .id(view.getId())
                    .gameId(view.getGameId())
                    .fields(existingFieldTOS)
                    .build();
        } catch (SqlRepositoryException e) {
            LOGGER.error("Cannot obtain fields for view with id " + view.getId(), e);
        }

        return null;
    }

    private ExistingFieldTO mapToExistingField(Field field) {
        List<String> values = Arrays.stream(field.getValues().split(",")).toList();

        return ExistingFieldTO
                .builder()
                .id(field.getId())
                .values(values)
                .name(field.getName())
                .fieldType(FieldTypeEnum.valueOf(field.getType()))
                .description(field.getDescription())
                .build();
    }
}
