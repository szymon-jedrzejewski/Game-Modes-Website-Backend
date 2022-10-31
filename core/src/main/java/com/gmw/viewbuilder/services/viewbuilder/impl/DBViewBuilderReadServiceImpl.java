package com.gmw.viewbuilder.services.viewbuilder.impl;

import com.gmw.model.Field;
import com.gmw.model.View;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.viewbuilder.services.DBService;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderReadService;
import com.gmw.viewbuilder.tos.ExistingFieldTO;
import com.gmw.viewbuilder.tos.ExistingViewTO;

import java.util.Arrays;
import java.util.List;

public class DBViewBuilderReadServiceImpl extends DBService implements DBViewBuilderReadService {

    public DBViewBuilderReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public ExistingViewTO obtainViewById(Long viewId) {
        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepositoryManager();
        QuerySpec querySpec = new QuerySpec();
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, viewId));
        querySpec.setClazz(View.class);
        querySpec.setTableName("views");

        List<View> views = viewRepositoryManager.find(querySpec);

        if (!views.isEmpty()) {
            return mapToExistingView(views.get(0));
        }

        return null;
    }

    @Override
    public ExistingViewTO obtainViewByGameId(Long gameId) {
        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepositoryManager();
        QuerySpec querySpec = new QuerySpec();
        querySpec.append(QueryOperator.WHERE, new SearchCondition("game_id", Operator.EQUAL_TO, gameId));
        querySpec.setClazz(View.class);
        querySpec.setTableName("views");

        List<View> views = viewRepositoryManager.find(querySpec);

        if (!views.isEmpty()) {
            return mapToExistingView(views.get(0));
        }

        return null;
    }

    private ExistingViewTO mapToExistingView(View view) {
        QuerySpec querySpec = new QuerySpec();
        querySpec.append(QueryOperator.WHERE, new SearchCondition("view_id", Operator.EQUAL_TO, view.getId()));
        querySpec.setTableName("fields");
        querySpec.setClazz(Field.class);

        List<ExistingFieldTO> existingFieldTOS = getRepositoryManager()
                .getFieldRepositoryManager()
                .find(querySpec)
                .stream()
                .map(this::mapToExistingField).toList();

        return ExistingViewTO
                .builder()
                .id(view.getId())
                .gameId(view.getGameId())
                .fields(existingFieldTOS)
                .build();
    }

    private ExistingFieldTO mapToExistingField(Field field) {
        List<String> values = Arrays.stream(field.getValues().split(",")).toList();

        return ExistingFieldTO
                .builder()
                .id(field.getId())
                .values(values)
                .name(field.getName())
                .fieldType(field.getFieldType())
                .description(field.getDescription())
                .build();
    }
}
