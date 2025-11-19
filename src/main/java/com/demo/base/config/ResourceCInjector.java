package com.demo.base.config;

import com.demo.base.domain.User;
import com.demo.base.repository.ResourceFinder;
import com.demo.base.util.ResourceByID;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import java.io.Serializable;
import java.util.Map;
@Component
public class ResourceCInjector implements HandlerMethodArgumentResolver {
    private final ApplicationContext applicationContext;
    @Autowired
    public ResourceCInjector(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ResourceByID.class) && parameter.getParameterType().isAssignableFrom(User.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object resolveArgument(@Nonnull MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        ResourceByID anno = parameter.getParameterAnnotation(ResourceByID.class);
        Map<String, String> path = (Map<String, String>) webRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, NativeWebRequest.SCOPE_REQUEST);
        assert anno != null;
        ResourceFinder<?> finder = applicationContext.getBean(anno.finder());


        if (path != null){
            Serializable id = path.get(anno.pathVariable());

            if (id != null)
                return finder.get(id);
        }
        return null;
    }
}
