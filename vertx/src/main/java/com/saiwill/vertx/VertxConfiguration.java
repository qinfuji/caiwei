package com.saiwill.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VertxConfiguration {

    @Autowired
    DeployConfiguration deployConfig;

    @Bean
    public VertxOptions vertxOptions(){
        VertxOptions options = new VertxOptions();
        options.setEventLoopPoolSize(deployConfig.getEventLoopPoolSize());
        options.setWorkerPoolSize(deployConfig.getWorkerPoolSize());
        return options;
    }

     @Bean
     public Vertx  vertx(){
            return Vertx.vertx(vertxOptions());
     }
}
