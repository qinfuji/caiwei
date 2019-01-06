package com.saiwill.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value={"application-vertx.properties","application.properties"})
@ConfigurationProperties("vertx")
@Configuration
public class VertxConfiguration {

    /**
     * eventloop线程池大小
     */
    private  int eventLoopPoolSize;

    /**
     * 工作线程大小
     */
    private int workerPoolSize;


    public int getEventLoopPoolSize() {
        return eventLoopPoolSize;
    }

    public void setEventLoopPoolSize(int eventLoopPoolSize) {
        this.eventLoopPoolSize = eventLoopPoolSize;
    }

    public int getWorkerPoolSize() {
        return workerPoolSize;
    }

    public void setWorkerPoolSize(int workerPoolSize) {
        this.workerPoolSize = workerPoolSize;
    }

    @Bean
    public VertxOptions vertxOptions(){
        VertxOptions options = new VertxOptions();
        options.setEventLoopPoolSize(getEventLoopPoolSize());
        options.setWorkerPoolSize(getWorkerPoolSize());
        return options;
    }

    @Bean
     public Vertx  vertx(){
         VertxOptions options = new VertxOptions();
         options.setEventLoopPoolSize(getEventLoopPoolSize());
         options.setWorkerPoolSize(getWorkerPoolSize());
         return Vertx.vertx(vertxOptions());
     }
}
