package com.alten.products.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class ApplicationProperties {
    private ServerProperties server;
    private String portt;

    public ServerProperties getServer() { return server; }
    public void setServer(ServerProperties server) { this.server = server; }

    public String getPortt() { return portt; }
    public void setPortt(String portt) { this.portt = portt; }
}

