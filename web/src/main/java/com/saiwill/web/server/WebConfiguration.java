package com.saiwill.web.server;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "we")
public class WebConfiguration {
     private  int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
