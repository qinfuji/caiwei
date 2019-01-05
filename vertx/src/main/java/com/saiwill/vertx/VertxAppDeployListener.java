package com.saiwill.vertx;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.VerticleFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Map;

public class VertxAppDeployListener implements ApplicationListener {

    Logger LOG = LoggerFactory.getLogger(VertxAppDeployListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
         if(applicationEvent instanceof ApplicationReadyEvent){
             ApplicationContext context = ((ApplicationReadyEvent) applicationEvent).getApplicationContext();
         }
    }

    void deploy(ApplicationContext context){
        VerticleFactory factory = context.getBean(SpringVerticleFactory.class);
        /*获取vertx实例*/
        Vertx vertx = context.getBean(Vertx.class);
        /*注册verticle工厂*/
        vertx.registerVerticleFactory(factory);
        /*获取vertx的options*/
        VertxOptions options = context.getBean(VertxOptions.class);

        /*部署verticle*/
        Map<String, Verticle> verticleMap = context.getBeansOfType(Verticle.class);

        DeploymentOptions deploymentOptions = new DeploymentOptions()
                .setInstances(options.getEventLoopPoolSize());
        if (verticleMap != null && !verticleMap.isEmpty()) {
            //部署Verticle
            for (String verticleName : verticleMap.keySet()) {
                String clazzName = verticleMap.get(verticleName).getClass().getName();
                clazzName = factory.prefix() + ":" + clazzName;
                if (deploymentOptions != null) {
                    vertx.deployVerticle(clazzName, deploymentOptions);
                } else {
                    vertx.deployVerticle(clazzName);
                }
            }
        }
    }
}
