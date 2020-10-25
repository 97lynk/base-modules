//package io.a97lynk.navigator.config;
//
//import io.a97lynk.navigator.config.interceptor.MultiTenantInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class MvcServerConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private MultiTenantInterceptor multiTenantInterceptor;
//
//   @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(multiTenantInterceptor);
//    }
//}
