package com.foody.payment.config;


import com.foody.payment.baseEndpoints.objectify.OfyService;
import com.foody.payment.endpoints.PaymentEndpoint;
import com.foody.payment.entities.Payment;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.util.jackson.ObjectifyJacksonModule;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class AppConfig extends Application {
    private static final ObjectifyFactory ofyService = new ObjectifyFactory();

    public AppConfig() {

        registringOfEntities();

    }

    private void registringOfEntities() {

        OfyService.factory().register(Payment.class);
    }

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();

        // registring com.endpoints

        classes.add(PaymentEndpoint.class);

        // json com.config
        classes.add(JacksonObjectResolver.class);
        classes.add(ObjectifyJacksonModule.class);

        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<Object>();



        return singletons;
    }
}