package com.gmw.services;

import com.gmw.services.exceptions.ServiceManagerFactoryException;

public interface ServiceManagerFactory{
    ServiceManager createSqlServiceManager() throws ServiceManagerFactoryException;
}
