package com.authserver.api.config;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceContextConfig {

    @Bean
    public DatastoreService cloudDatastoreService() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        return datastore;
    }
}
