package com.example.mshd_project.core.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//全局配置类--配置跨域请求
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
     public void addCorsMappings(CorsRegistry registry){
          registry.addMapping("/**")//域访问路径
                  .allowedOrigins("http://localhost:8080","null")//请求来源
                  .allowedMethods("GET","PUT","POST","OPTIONS","DELETE")//方法
                  .maxAge(3600)//最大时间
                  .allowCredentials(true);//允许携带
    }
}
