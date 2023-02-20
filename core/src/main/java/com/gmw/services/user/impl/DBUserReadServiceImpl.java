package com.gmw.services.user.impl;

import com.gmw.coverters.UserConverter;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.User;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.DBService;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.user.DBUserReadService;
import com.gmw.user.tos.ExistingUserTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DBUserReadServiceImpl extends DBService implements DBUserReadService {
    private static final Logger LOGGER = LogManager.getLogger();

    public DBUserReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public ExistingUserTO obtainUserByEmail(String email) throws ResourceNotFoundException {
        Repository<User> repository = getRepositoryManager().getUserRepository();
        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName(new User().getTableName());
        querySpec.setClazz(User.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("email", Operator.EQUAL_TO, List.of(email)));

        return ServiceUtils.find(repository, new UserConverter(), querySpec).get(0);
    }

    @Override
    public String obtainUserRoleByUserId(Long id) throws ResourceNotFoundException {
        Repository<User> repository = getRepositoryManager().getUserRepository();
        QuerySpec querySpec = prepareQueryToFindById(id);

        try {
            List<User> users = repository.find(querySpec);

            if (users == null || users.isEmpty()) {
                LOGGER.error("User not found!");
                throw new ResourceNotFoundException();
            }

            return users.get(0).getRole();

        } catch (SqlRepositoryException e) {
            LOGGER.error("User not found!");
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public List<ExistingUserTO> obtainUsersByIds(List<Long> ids) throws ResourceNotFoundException {
        Repository<User> repository = getRepositoryManager().getUserRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName(new User().getTableName());
        querySpec.setClazz(User.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.IN,
                ids.stream().map(id -> (Object) id).toList()));

        return ServiceUtils.find(repository, new UserConverter(), querySpec);
    }

    @Override
    public ExistingUserTO obtainUserById(Long userId) throws ResourceNotFoundException {
        Repository<User> repository = getRepositoryManager().getUserRepository();
        QuerySpec querySpec = prepareQueryToFindById(userId);

        return ServiceUtils.find(repository, new UserConverter(), querySpec).get(0);
    }

    private static QuerySpec prepareQueryToFindById(Long id) {
        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName(new User().getTableName());
        querySpec.setClazz(User.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, List.of(id)));
        return querySpec;
    }
}
