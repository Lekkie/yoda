package com.avantir.yoda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by lekanomotayo on 05/03/2018.
 */
@Configuration
@EnableWebMvc
public class WebMVCConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/home").setViewName("home");
        //registry.addViewController("/").setViewName("home");
        //registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }


    /*
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        //resolver.setPrefix("/jsp/");
        //resolver.setSuffix(".jsp");
        return resolver;
    }
    */

}
