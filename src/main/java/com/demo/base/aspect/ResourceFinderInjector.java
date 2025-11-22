package com.demo.base.aspect;

import com.demo.base.util.annotation.ResourceFinder;
import com.demo.base.util.annotation.ResourceInjector;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.annotation.Annotation;

@Aspect
@Component
public class ResourceFinderInjector {
    private final ApplicationContext applicationContext;

    @Autowired
    public ResourceFinderInjector(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Around("execution(* *.*(.., @com.demo.base.util.annotation.ResourceInjector (*), ..))")
    public Object resourceInject(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Object[] args = pjp.getArgs();
        String[] parameterNames = signature.getParameterNames();

        for (int i = 0; i < args.length; i++) {
            Annotation[] annotations = signature.getMethod().getParameterAnnotations()[i];

            for (Annotation annotation : annotations) {
                if (annotation instanceof ResourceInjector resourceInjector) {
                    Class<? extends ResourceFinder<?>> finder = resourceInjector.finder();
                    Object entity = applicationContext.getBean(finder).getEntity((Serializable) args[i]);

                    for (int j = 0; j < parameterNames.length; j++)
                        if (parameterNames[j].equals(resourceInjector.target())) {
                            args[j] = entity;
                            break;
                        }
                }
            }
        }
        return pjp.proceed(args);
    }
}