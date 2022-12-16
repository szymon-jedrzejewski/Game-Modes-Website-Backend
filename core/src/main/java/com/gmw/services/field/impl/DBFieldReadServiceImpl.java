package com.gmw.services.field.impl;

import com.gmw.model.Field;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.DBService;
import com.gmw.services.ServiceUtils;
import com.gmw.services.TOConverter;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.field.DBFieldReadService;
import com.gmw.view.enums.FieldTypeEnum;
import com.gmw.view.tos.ExistingFieldTO;

import java.util.List;

public class DBFieldReadServiceImpl extends DBService implements DBFieldReadService, TOConverter<ExistingFieldTO, Field> {
    public DBFieldReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public List<ExistingFieldTO> obtainFieldsByViewId(Long viewId) throws ResourceNotFoundException {
        Repository<Field> repository = getRepositoryManager().getFieldRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("fields");
        querySpec.setClazz(Field.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("view_id", Operator.EQUAL_TO, viewId));

        return ServiceUtils.find(repository, this, querySpec);
    }

    @Override
    public ExistingFieldTO convert(Field field) {
        return ExistingFieldTO
                .builder()
                .id(field.getId())
                .fieldType(FieldTypeEnum.valueOf(field.getType()))
                .description(field.getDescription())
                .name(field.getName())
                .label(field.getLabel())
                .build();
    }
}
