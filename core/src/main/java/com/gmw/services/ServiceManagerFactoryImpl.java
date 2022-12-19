package com.gmw.services;

import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.exceptions.ServiceManagerFactoryException;
import com.gmw.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class ServiceManagerFactoryImpl implements ServiceManagerFactory {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public ServiceManager createSqlServiceManager() throws ServiceManagerFactoryException {
        try {
            return new SqlServiceManager(new SqlRepositoryManager(JDBCUtils.getConnection()));
        } catch (SQLException e) {
            LOGGER.error("Error during sql persistence manager creation!");

            throw new ServiceManagerFactoryException(e);
        }
    }

}
