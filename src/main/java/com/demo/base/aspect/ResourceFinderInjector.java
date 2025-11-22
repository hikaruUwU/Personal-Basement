package com.demo.base.aspect;

import com.demo.base.util.annotation.Inject;
import com.demo.base.util.annotation.InjectKey;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.util.SoftHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class ResourceFinderInjector {
    private final ApplicationContext applicationContext;
    private static final Map<Method, Object[]> REFLECT_CACHE = new SoftHashMap<>();
    private static final Map<Class<?>, Field> INJECT_KEY_CACHE = new SoftHashMap<>();

    @Autowired
    public ResourceFinderInjector(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Around("execution(* *.*(.., @com.demo.base.util.annotation.Inject (*), ..))")
    public Object resourceInject(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        Object[] cachedData = REFLECT_CACHE.computeIfAbsent(method, m -> {
            Class<?>[] pTypes = m.getParameterTypes();
            Annotation[][] pAnnotations = m.getParameterAnnotations();
            return new Object[]{pAnnotations, pTypes};
        });

        Annotation[][] parameterAnnotations = (Annotation[][]) cachedData[0];
        Class<?>[] parameterTypes = (Class<?>[]) cachedData[1];

        Object[] args = pjp.getArgs().clone();

        for (int i = 0; i < args.length; i++) {
            Inject injectSource = null;
            for (Annotation annotation : parameterAnnotations[i])
                if (annotation instanceof Inject) {
                    injectSource = (Inject) annotation;
                    break;
                }

            if (injectSource == null) continue;

            Object entityInstance = args[i];
            if (entityInstance == null) continue;

            Class<?> entityClass = entityInstance.getClass();

            Field keyField = INJECT_KEY_CACHE.computeIfAbsent(entityClass, c -> {

                for (Field field : c.getDeclaredFields())
                    if (field.isAnnotationPresent(InjectKey.class))
                        return field;

                return null;
            });

            if (keyField == null) continue;

            keyField.setAccessible(true);

            Object rawId = keyField.get(entityInstance);
            if (!(rawId instanceof Serializable inputId)) continue;

            Object freshEntity = applicationContext.getBean(injectSource.finder()).getEntity(inputId);

            if (freshEntity == null) continue;

            Class<?> freshType = freshEntity.getClass();
            if (parameterTypes[i].isAssignableFrom(freshType))
                args[i] = freshEntity;
        }
        return pjp.proceed(args);
    }
}