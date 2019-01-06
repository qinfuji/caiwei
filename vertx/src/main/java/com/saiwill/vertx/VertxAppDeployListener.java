package com.saiwill.vertx;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.awt.event.ActionEvent;
import java.util.Map;

public class VertxAppDeployListener implements ApplicationListener {

    Logger LOG = LoggerFactory.getLogger(VertxAppDeployListener.class);


    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ApplicationReadyEvent) {
            ApplicationContext context = ((ApplicationReadyEvent) applicationEvent).getApplicationContext();
            deploy(context);
        }
    }

    static void deploy(ApplicationContext context) {

        Vertx vertx = context.getBean("vertx" ,Vertx.class);
        VertxOptions vertxOptions = context.getBean(VertxOptions.class);
        Map<String, Verticle> verticleMap = context.getBeansOfType(Verticle.class);
        /*部署verticle*/
        DeploymentOptions deploymentOptions = new DeploymentOptions()
                .setInstances(vertxOptions.getEventLoopPoolSize());
        if (verticleMap != null && !verticleMap.isEmpty()) {
            //部署Verticle
            for (String verticleName : verticleMap.keySet()) {
                String clazzName = verticleMap.get(verticleName).getClass().getName();
                clazzName = "springContext:" + clazzName;
                if (deploymentOptions != null) {
                    vertx.deployVerticle(clazzName, deploymentOptions);
                } else {
                    vertx.deployVerticle(clazzName);
                }
            }
        }
    }
}
