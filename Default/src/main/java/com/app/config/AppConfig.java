package com.app.config;


import com.app.endpoints.FoodEndpoint;
import com.app.endpoints.HotelEndpoint;
import com.app.exception.mapper.*;
import com.app.entity.Food;
import com.app.entity.Hotel;
import com.app.objectify.OfyService;
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

        OfyService.factory().register(Hotel.class);
        OfyService.factory().register(Food.class);
    }

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();

        // registring com.endpoints


        classes.add(HotelEndpoint.class);
        classes.add(FoodEndpoint.class);

        // json com.config
        classes.add(JacksonObjectResolver.class);
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
        singletons.add(new GenericExceptionMapper());


        // for now allowing all based on requested origins

        return singletons;
    }
}