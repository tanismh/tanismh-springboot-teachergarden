package com.hwb.tg.Config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 何伟斌
 * @date 2020/12/5 21:35
 */

@Configuration
public class CorsConfig {

//    @Bean
    public WebMvcConfigurer CORSConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
//                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        //设置是否允许跨域传cookie
                        .allowCredentials(true)
                        //设置缓存时间，减少重复响应
                        .maxAge(3600);
            }
        };
    }


    @Bean
    public FilterRegistrationBean corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        // 允许cookies跨域
        config.setAllowCredentials(true);

        FilterRegistrationBean bean = new FilterRegistrationBean(new Filter() {
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                HttpServletRequest request = (HttpServletRequest)servletRequest;
                HttpServletResponse response = (HttpServletResponse)servletResponse;
                response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
                response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, token");
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.addHeader("Access-Control-Allow-Credentials", "true");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                //此行代码确保请求可以继续执行至Controller
                System.out.println(System.getProperty("user.dir"));
                filterChain.doFilter(request, response);

            }
        });
        // 设置监听器的优先级
        bean.setOrder(0);

        return bean;
    }
}
