package com.alten.products.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class ServerProperties {
    private int port;

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }
}
