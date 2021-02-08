package com.rocky.boot.api.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author rocky
 * Description:
 * Created in 2021/2/8
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${spring.swagger.api.enable}")
    private Boolean swaggerEnable;

    /**
     * 静态资源处理
     *
     * @param registry 静态资源注册器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (swaggerEnable) {
            // 解决swagger静态资源无法访问
            registry.addResourceHandler("/").addResourceLocations("classpath:/static/");
            registry.addResourceHandler("/csrf").addResourceLocations("classpath:/static/");
            registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }
}
