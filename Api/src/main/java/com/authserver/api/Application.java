package com.authserver.api;

import com.authserver.api.services.OfyService;
import com.commons.entity.App;
import com.commons.entity.Contact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public Application(){
        registringOfEntities();
    }

    private void registringOfEntities() {
        OfyService.factory().register(Contact.class);
        OfyService.factory().register(App.class);
    }
}
