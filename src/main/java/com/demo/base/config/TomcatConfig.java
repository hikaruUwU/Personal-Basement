package com.demo.base.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class TomcatConfig {
    private final Executor executor = Executors.newVirtualThreadPerTaskExecutor();

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(connector -> {
            connector.getProtocolHandler().setExecutor(executor);
        });
        return tomcat;
    }
}
