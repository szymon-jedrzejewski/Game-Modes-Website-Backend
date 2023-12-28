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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DBRatingReadServiceImpl extends DBService implements DBRatingReadService {
    private static final Logger LOGGER = LogManager.getLogger();

    public DBRatingReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Double obtainRatingForMod(Long modId) {
        Repository<Rating> repository = getRepositoryManager().getRatingRepository();
        QuerySpec querySpec = prepareQuerySpec(modId);

        try {
            return ServiceUtils
                    .find(repository, new RatingConverter(), querySpec)
                    .stream()
                    .mapToDouble(ExistingRatingTO::getRating)
                    .average()
                    .orElse(0);
        } catch (ResourceNotFoundException e) {
            LOGGER.warn("There is no rating for this mod: " + modId);
        }

        return 0.0;
    }

    @Override
    public List<Long> obtainRatingsIdsByModId(Long modId) throws ResourceNotFoundException {
        Repository<Rating> repository = getRepositoryManager().getRatingRepository();
        QuerySpec querySpec = prepareQuerySpec(modId);

        return ServiceUtils
                .find(repository, new RatingConverter(), querySpec)
                .stream()
                .map(ExistingRatingTO::getId)
                .toList();
    }

    @Override
    public Long obtainUserIdByRatingId(Long id) throws ResourceNotFoundException {
        Repository<Rating> repository = getRepositoryManager().getRatingRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName(new Rating().getTableName());
        querySpec.setClazz(Rating.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, List.of(id)));

        return ServiceUtils.find(repository, new RatingConverter(), querySpec).get(0).getUserId();
    }

    private QuerySpec prepareQuerySpec(Long modId) {
        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName(new Rating().getTableName());
        querySpec.setClazz(Rating.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("mod_id", Operator.EQUAL_TO, List.of(modId)));
        return querySpec;
    }
}
