package com.saiwill.web.server;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value={"application-vertx-web.properties","application.properties"})
@ConfigurationProperties("vertx.web")
@Configuration
@Component
public class VetxWebConfiguration {
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
