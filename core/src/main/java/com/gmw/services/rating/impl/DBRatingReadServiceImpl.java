package com.gmw.services.rating.impl;

import com.gmw.coverters.RatingConverter;
import com.gmw.model.Rating;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.rating.tos.ExistingRatingTO;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.DBService;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.rating.DBRatingReadService;

public class DBRatingReadServiceImpl extends DBService implements DBRatingReadService {
    public DBRatingReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Double obtainRatingForMod(Long modId) throws ResourceNotFoundException {
        Repository<Rating> repository = getRepositoryManager().getRatingRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName(new QuerySpec().getTableName());
        querySpec.setClazz(Rating.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("mod_id", Operator.EQUAL_TO, modId));

        return ServiceUtils
                .find(repository, new RatingConverter(), querySpec)
                .stream()
                .mapToDouble(ExistingRatingTO::getRating)
                .average()
                .orElseThrow(ResourceNotFoundException::new);
    }
}
