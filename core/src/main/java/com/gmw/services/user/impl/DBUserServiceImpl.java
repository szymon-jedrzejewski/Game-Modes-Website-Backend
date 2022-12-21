package com.gmw.services.user.impl;

import com.gmw.coverters.UserConverter;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.User;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.user.DBUserService;
import com.gmw.user.enums.RoleEnum;
import com.gmw.user.tos.ExistingUserTO;
import com.gmw.user.tos.NewUserTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBUserServiceImpl extends DBUserReadServiceImpl implements DBUserService {
    private static final Logger LOGGER = LogManager.getLogger();

    public DBUserServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createUser(NewUserTO userTO) throws ResourceNotCreatedException {
        Repository<User> repository = getRepositoryManager().getUserRepository();

        UserConverter converter = new UserConverter();
        User user = converter.convertToModel(userTO);

        ServiceUtils.create(repository, user);
    }

    @Override
    public void updateUser(ExistingUserTO userTO) throws ResourceNotUpdatedException {
        Repository<User> repository = getRepositoryManager().getUserRepository();

        UserConverter converter = new UserConverter();
        User user = converter.convertToModel(userTO);
        user.setId(userTO.getId());

        ServiceUtils.update(repository, user);
    }

    @Override
    public void deleteUser(Long userId) throws ResourceNotDeletedException {
        Repository<User> repository = getRepositoryManager().getUserRepository();

        User user = new User();
        user.setId(userId);
        user.setEmail(null);
        user.setPassword(null);
        user.setAvatar(null);
        user.setRole(null);
        user.setName("deleted_user");

        try {
            repository.update(user);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Cannot delete user with id: " + userId, e);
            throw new ResourceNotDeletedException();
        }
    }

    @Override
    public void promoteToAdmin(ExistingUserTO userTO) throws ResourceNotUpdatedException {
        Repository<User> repository = getRepositoryManager().getUserRepository();
        UserConverter converter = new UserConverter();

        User user = converter.convertToModel(userTO);
        user.setId(userTO.getId());
        user.setRole(RoleEnum.ADMIN.toString());

        try {
            repository.update(user);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Cannot promote to admin user with id: " + userTO.getId(), e);
            throw new ResourceNotUpdatedException();
        }
    }

    @Override
    public void demoteFromAdmin(ExistingUserTO userTO) throws ResourceNotUpdatedException {
        Repository<User> repository = getRepositoryManager().getUserRepository();
        UserConverter converter = new UserConverter();

        User user = converter.convertToModel(userTO);
        user.setId(userTO.getId());
        user.setRole(RoleEnum.USER.toString());

        try {
            repository.update(user);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Cannot promote to admin user with id: " + userTO.getId(), e);
            throw new ResourceNotUpdatedException();
        }
    }
}
