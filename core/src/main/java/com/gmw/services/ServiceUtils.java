package com.gmw.services;

import com.gmw.coverters.ModelConverter;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.persistence.QuerySpec;
import com.gmw.repository.Repository;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ServiceUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    public static <T> void create(Repository<T> repository, T t) throws ResourceNotCreatedException {
        try {
            Long id = repository.create(t);

            if (id == null) {
                throw new ResourceNotCreatedException();
            }
        } catch (SqlRepositoryException e) {
            LOGGER.error("Cannot create new " + t.getClass().getSimpleName().toLowerCase(), e);
            throw new ResourceNotCreatedException();
        }
    }

    public static <T> void delete(Repository<T> repository, Long id) throws ResourceNotDeletedException {
        try {
            repository.delete(id);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Cannot delete resource with id: " + id, e);
            throw new ResourceNotDeletedException(e);
        }
    }

    public static <T> void update(Repository<T> repository, T t) throws ResourceNotUpdatedException {
        try {
            repository.update(t);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Cannot update " + t.getClass().getSimpleName().toLowerCase(), e);
            throw new ResourceNotUpdatedException(e);
        }
    }

    public static <T, U> List<U> find(Repository<T> repository, ModelConverter<U, T> converter, QuerySpec querySpec) throws ResourceNotFoundException {
        try {
            List<U> list = repository.find(querySpec).stream().map(converter::convertToTO).toList();

            if (list.isEmpty()) {
                LOGGER.error("No resources found!");
                throw new ResourceNotFoundException();
            }

            return list;
        } catch (SqlRepositoryException e) {
            LOGGER.error("Error during obtaining resources!", e);
            throw new ResourceNotFoundException();
        }
    }
}
