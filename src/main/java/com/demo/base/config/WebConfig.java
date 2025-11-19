package com.demo.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final PassInterceptor passInterceptor;
    private final Executor executor = Executors.newVirtualThreadPerTaskExecutor();
    private final ResourceCInjector resourceCInjector;

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(connector -> connector.getProtocolHandler().setExecutor(executor));
        return tomcat;
    }

    @Autowired
    public WebConfig(PassInterceptor passInterceptor, ResourceCInjector resourceCInjector) {
        this.passInterceptor = passInterceptor;
        this.resourceCInjector = resourceCInjector;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(resourceCInjector);
    }
}
