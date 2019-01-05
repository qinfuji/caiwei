package com.saiwill.vertx;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "deploy")
public class DeployConfiguration {
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
}
