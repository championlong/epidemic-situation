package com.outbreak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@Configuration
public class CorsFilterConfiguration {
    @Bean
    public CorsFilter corsFilter(){

        // 初始化配置对象
        CorsConfiguration configuration = new CorsConfiguration();
        // 允许跨越的域名，可以设置多个。*代表所有域名，如果要携带cookie，一定不能设置为*
        configuration.addAllowedOrigin("http://localhost:8080");
        // 允许携带cookie
        configuration.setAllowCredentials(true);
        // 允许所有请求方式跨域访问
        configuration.addAllowedMethod("*");
        // 允许携带所有头信息跨域访问
        configuration.addAllowedHeader("*");

        // 初始化配置源对象
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        // 拦截所有请求，校验是否允许跨域
        configurationSource.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(configurationSource);
    }
}
