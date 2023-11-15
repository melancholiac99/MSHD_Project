package com.example.mshd_project.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 *
 * 全局配置类--配置跨域请求
 * @author zyt
**/
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
     public void addCorsMappings(CorsRegistry registry){
        //域访问路径
          registry.addMapping("/**")
                  //请求来源, 其实就是允许所有跨域请求
                  .allowedOriginPatterns("*")
                  //方法
                  .allowedMethods("GET","PUT","POST","OPTIONS","DELETE")
                  //最大时间
                  .maxAge(3600)
                  //允许携带
                  .allowCredentials(true)
                  .allowedHeaders("*")
                  .exposedHeaders("*");
    }
}
