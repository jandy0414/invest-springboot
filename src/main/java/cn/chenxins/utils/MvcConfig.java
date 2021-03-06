package cn.chenxins.utils;


import cn.chenxins.authorization.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 配置类，增加自定义拦截器和解析器
 * @see cn.chenxins.authorization.interceptor.AuthorizationInterceptor
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
//public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("file:C:/upload/");
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("http://admin.chenxins.cn","http://blog.chenxins.cn","http://localhost")
//                .allowedOrigins("*")
                .allowedMethods("*");

    }

}
