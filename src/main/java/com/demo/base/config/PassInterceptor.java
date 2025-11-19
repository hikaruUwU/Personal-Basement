package com.demo.base.config;

import com.demo.base.util.Restrict;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class PassInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Restrict restrictor = handlerMethod.getMethod().getAnnotation(Restrict.class);

        if (restrictor == null)
            restrictor = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Restrict.class);

        if (restrictor != null && request.getSession(false) == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        } else
            return true;
    }

    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
