package com.foody.order.config;



import com.foody.order.baseEndpoints.objectify.OfyService;
import com.foody.order.endpoints.CartEndpoint;
import com.foody.order.entities.Cart;
import com.foody.order.entities.Order;
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

        OfyService.factory().register(Cart.class);
        OfyService.factory().register(Order.class);
    }

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();

        // registring com.endpoints

            classes.add(CartEndpoint.class);
//        classes.add(HotelEndpoint.class);
//        classes.add(FoodEndpoint.class);

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


        // for now allowing all based on requested origins

        return singletons;
    }
}