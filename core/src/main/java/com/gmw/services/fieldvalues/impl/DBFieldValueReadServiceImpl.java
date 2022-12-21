package com.gmw.services.fieldvalues.impl;

import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.fieldvalue.tos.SearchFieldValue;
import com.gmw.model.FieldValue;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.DBService;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.fieldvalues.DBFieldValueReadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DBFieldValueReadServiceImpl extends DBService implements DBFieldValueReadService {
    private static final Logger LOGGER = LogManager.getLogger();

    public DBFieldValueReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public List<Long> obtainModsIsBySearchValues(List<SearchFieldValue> searchFieldValues) throws ResourceNotFoundException {
        Repository<FieldValue> repository = getRepositoryManager().getFieldValueRepository();
        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName(new FieldValue().getTableName());
        querySpec.setClazz(FieldValue.class);

        if (searchFieldValues == null || searchFieldValues.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            SearchFieldValue searchFieldValue = searchFieldValues.get(0);
            querySpec.appendWithOpeningRoundBracket(QueryOperator.WHERE, new SearchCondition("field_id", Operator.EQUAL_TO, searchFieldValue.getFieldId()));
            querySpec.appendWithClosingRoundBracket(QueryOperator.AND, new SearchCondition("value", Operator.EQUAL_TO, searchFieldValue.getValue()));

            if (searchFieldValues.size() > 1)
            {
                for (int i = 1; i < searchFieldValues.size(); i++) {
                    String value = searchFieldValues.get(i).getValue();
                    Long fieldId = searchFieldValues.get(i).getFieldId();
                    if (value != null && fieldId != null) {
                        querySpec.appendWithOpeningRoundBracket(QueryOperator.OR, new SearchCondition("field_id",
                                Operator.EQUAL_TO, fieldId));
                        querySpec.appendWithClosingRoundBracket(QueryOperator.AND, new SearchCondition("value",
                                Operator.EQUAL_TO, value));
                    }
                }
            }

        }

        try {
            List<Long> modsIds = repository.find(querySpec).stream().map(FieldValue::getModId).toList();

            if (modsIds.isEmpty())
            {
                throw new ResourceNotFoundException();
            }

            return modsIds;
        } catch (SqlRepositoryException e) {
            LOGGER.error("Error during obtaining mods ids!", e);
            throw new ResourceNotFoundException();
        }
    }
}
