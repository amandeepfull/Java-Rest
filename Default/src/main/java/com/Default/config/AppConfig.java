package com.Default.config;

import com.Default.endpoints.apis.AppEndpoint;
import com.Default.endpoints.auth.AuthEndpoint;
import com.Default.endpoints.auth.ServiceEndpoint;
import com.commons.config.JacksonObjectResolver;
import com.commons.entity.App;
import com.commons.entity.Contact;
import com.commons.entity.Token;
import com.commons.exception.mapper.*;
import com.commons.filters.ApiKeyFilter;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.util.jackson.ObjectifyJacksonModule;
import com.commons.objectify.OfyService;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class AppConfig extends Application {
    private static final ObjectifyFactory ofyService = new ObjectifyFactory();
   public AppConfig(){

      registringOfEntities();

    }

    private void registringOfEntities() {

        /// registring the entities
        OfyService.factory().register(Contact.class);
        OfyService.factory().register(App.class);
        OfyService.factory().register(Token.class);
    }

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();

        // registring com.endpoints
        classes.add(ServiceEndpoint.class);
        classes.add(AppEndpoint.class);
        classes.add(AuthEndpoint.class);

        // json com.config
        classes.add(JacksonObjectResolver.class);
        classes.add(ObjectifyJacksonModule.class);

        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<Object>();


        // filters

        singletons.add(new ApiKeyFilter());
        //exception mappers
        singletons.add(new IllegalArgExceptionMapper());
        singletons.add(new ResourceNotFoundExceptionMapper());
        singletons.add(new ForbiddenExceptionMapper());
        singletons.add(new NotAllowedExceptionMapper());
        singletons.add(new JaxRsForbiddenExceptionMapper());
        singletons.add(new InvalidFormatExceptionMapper());
        singletons.add(new GenericExceptionMapper());

        // for now allowing all based on requested origins
        CorsFilter cors = new CorsFilter();
        cors.getAllowedOrigins().add("*");
        cors.setCorsMaxAge(1728000);
        cors.setAllowCredentials(false);

        singletons.add(cors);
        return singletons;
    }
}