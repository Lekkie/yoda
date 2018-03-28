package com.avantir.yoda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lekanomotayo on 05/03/2018.
 */
@Configuration
public class WebConfig {

    //https://spring.io/blog/2015/06/08/cors-support-in-spring-framework

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
                        //.allowedMethods("POST", "GET", "PUT", "PATCH", "DELETE")
                        //.allowedHeaders("*");
                /*
                registry.addMapping("/api/**")
                        .allowedOrigins("http://domain2.com")
                        .allowedMethods("PUT", "DELETE")
                        .allowedHeaders("header1", "header2", "header3")
                        .exposedHeaders("header1", "header2")
                        .allowCredentials(false).maxAge(3600);
                */
            }
        };
    }


    /*
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://domain1.com");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
    */

    @Bean
    public AuthenticationTrustResolver trustResolver() {
        return new AuthenticationTrustResolver() {

            @Override
            public boolean isRememberMe(final Authentication authentication) {
                return false;
            }

            @Override
            public boolean isAnonymous(final Authentication authentication) {
                return false;
            }
        };
    }


}
