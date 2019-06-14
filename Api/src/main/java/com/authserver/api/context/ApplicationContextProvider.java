package com.authserver.api.context;

import lombok.Synchronized;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware{

    private static ApplicationContext context;

    @Synchronized
    public synchronized static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public synchronized void setApplicationContext(ApplicationContext ac)
            throws BeansException {
        context = ac;
    }

}
