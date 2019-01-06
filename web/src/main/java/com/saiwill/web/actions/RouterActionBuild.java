package com.saiwill.web.actions;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.stereotype.Component;

@Component
public class RouterActionBuild {

    public void  build(RouteAction action ,Router router) {
          //这里分析Router注解信息，进行参数和返回值处理

    }

    class ActionHandler implements RouteHandler {

        public ActionHandler(RouteAction routerAction , Router router) {
             //
        }

        @Override
        public void handle(RoutingContext routingContext) {

        }
    }
}
