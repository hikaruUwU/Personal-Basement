package com.demo.base.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class TomcatConfig {
    private final Executor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Integer.MAX_VALUE,
            60L,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            Thread.ofVirtual().name("tomcat-executor",0).factory(),
            new ThreadPoolExecutor.DiscardPolicy()
    );



    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(connector -> {
            connector.getProtocolHandler().setExecutor(executor);
        });
        return tomcat;
    }
}
