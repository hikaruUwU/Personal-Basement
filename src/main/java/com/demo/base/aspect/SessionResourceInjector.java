package com.demo.base.aspect;

import com.demo.base.util.annotation.InjectViaSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.annotation.Annotation;

@Aspect
@Component
public class SessionResourceInjector {
    @Around("execution(* *.*(.., @com.demo.base.util.annotation.InjectViaSession (*), ..))")
    public Object resourceInject(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        if (attributes == null)
            return pjp.proceed();

        Object[] args = pjp.getArgs().clone();


        for (int i = 0; i < args.length; i++)
            for (Annotation annotation : signature.getMethod().getParameterAnnotations()[i])
                if (annotation instanceof InjectViaSession)
                    args[i] = attributes.getRequest().getSession(true).getAttribute(((InjectViaSession) annotation).key());

        return pjp.proceed(args);
    }
}
