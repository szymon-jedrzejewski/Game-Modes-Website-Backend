package com.gmw.viewbuilder.services;

public class ServiceManagerFactory {

    public static ServiceManager getSqlServiceManager() {
        return new SqlServiceManager();
    }
}
