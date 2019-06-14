package com.authserver.api.config;

import com.authserver.api.filter.ApiKeyFilter;
import com.googlecode.objectify.ObjectifyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    // api paths
    @Bean
    public FilterRegistrationBean apiFilterRegisteration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ApiKeyFilter());
        registration.addUrlPatterns("/api/*");
        registration.setOrder(1);
        return registration;
    }

    // ObjectifyFilter
    @Bean
    public FilterRegistrationBean objectifyFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ObjectifyFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);

        return registration;
    }
}
