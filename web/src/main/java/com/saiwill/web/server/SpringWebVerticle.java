package com.saiwill.web.server;

import com.saiwill.web.actions.RouteAction;
import com.saiwill.web.actions.RouterActionBuild;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.TimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class SpringWebVerticle extends AbstractVerticle {

    private static Logger LOGGER = LoggerFactory.getLogger(SpringWebVerticle.class);

    @Autowired
    VetxWebConfiguration config;

    @Autowired
    Map<String, RouteAction> actionMap;

    @Autowired
    RouterActionBuild routerActionBuild;

    @Override
    public void start() throws Exception {
        super.start();
        Router router = Router.router(vertx);
        routes(router);
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(config.getPort());
        LOGGER.info("web server startd with  port :" + config.getPort());
    }

    void routes(Router router) {

        commonRoutes(router);

        actionRoutes(router);
    }

    /**
     * 通用路由
     *
     * @param router 路由器
     */
    void commonRoutes(Router router) {

        /*心跳*/
        router.route("/heartbeat").handler(ctx -> {
            ctx.response().setStatusCode(200).end();
            ctx.response().close();
        });

        /*请求处理超时：最大10秒*/
        router.route().handler(TimeoutHandler.create(10000));

        /*post请求体*/
        router.route().handler(BodyHandler.create());

        /*api统一响应json格式Content-Type头*/
        router.route().handler(ctx -> {
            ctx.response().putHeader("Content-Type", "application/json;charset=utf-8");
            ctx.next();
        });

//        router.route("/content/accountDetail").handler(ctx -> {
//            ctx.response().putHeader("Access-Control-Allow-Origin", "*");
//            ctx.response().putHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
//            ctx.next();
//        });

        /*请求信息记录日志*/
        router.route().handler(ctx -> {
            String uri = ctx.request().uri();
            HttpMethod method = ctx.request().method();
            String path = ctx.request().path();
            String host = ctx.request().host();
            HttpVersion version = ctx.request().version();
            String remote = ctx.request().remoteAddress().host();
            String forwarded = ctx.request().getHeader("X-Forwarded-For");
            long begin = System.currentTimeMillis();
            ctx.addBodyEndHandler(v -> {
                int statusCode = ctx.response().getStatusCode();
                long end = System.currentTimeMillis();
                String dateTime = LocalDateTime.now()
                        .format(
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
                        );
                ctx.next();
            });
        });
    }

    void actionRoutes(Router router) {
        if (actionMap != null && !actionMap.isEmpty()) {
            actionMap.entrySet().stream()
                    /*转换handler*/
                    .forEach(entry -> {
                        RouteAction action = entry.getValue();
                        routerActionBuild.build(action, router);
                    });
        }
    }
}
