package com.authserver.app.config;

import com.authserver.app.endpoints.TestEndpoint;

import com.authserver.app.exception.mapper.*;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.util.jackson.ObjectifyJacksonModule;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class AppConfig extends Application {
    private static final ObjectifyFactory ofyService = new ObjectifyFactory();
   public AppConfig(){

      registringOfEntities();

    }


    private void registringOfEntities() {


    }

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();

        // registring com.endpoints

        classes.add(TestEndpoint.class);

        // json com.config

        classes.add(ObjectifyJacksonModule.class);

        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<Object>();


        // filters

      //  singletons.add(new CommonApiResponseFilter());

        //exception mappers
        singletons.add(new IllegalArgExceptionMapper());
        singletons.add(new ResourceNotFoundExceptionMapper());
        singletons.add(new ForbiddenExceptionMapper());
        singletons.add(new NotAllowedExceptionMapper());
        singletons.add(new JaxRsForbiddenExceptionMapper());
        singletons.add(new InvalidFormatExceptionMapper());
        singletons.add(new GenericExceptionMapper());


        // for now allowing all based on requested origins

        return singletons;
    }
}