package com.example.ncov_h.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");  //设置访问源位置
        corsConfiguration.addAllowedHeader("*");  //设置访问源请求头
        corsConfiguration.addAllowedMethod("*");   //设置访问源请求方式
        source.registerCorsConfiguration("/**",corsConfiguration); //对接口配置跨域设置
        return new CorsFilter(source);
    }
}
