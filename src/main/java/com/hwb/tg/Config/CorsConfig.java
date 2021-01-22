package com.hwb.tg.Config;

import com.hwb.tg.Controller.TeacherController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 何伟斌
 * @date 2020/12/5 21:35
 */

@Configuration
public class CorsConfig {
    protected static final Logger logger = LoggerFactory.getLogger(CorsConfig.class);

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
                //System.out.println(System.getProperty("user.dir"));
                filterChain.doFilter(request, response);
                String ip = request.getHeader("x-forwarded-for");
                //System.out.println("x-forwarded-for ip: " + ip);
                if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                    // 多次反向代理后会有多个ip值，第一个ip才是真实ip
                    if( ip.indexOf(",")!=-1 ){
                        ip = ip.split(",")[0];
                    }
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("Proxy-Client-IP");
                    //System.out.println("Proxy-Client-IP ip: " + ip);
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                    //System.out.println("WL-Proxy-Client-IP ip: " + ip);
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_CLIENT_IP");
                    //System.out.println("HTTP_CLIENT_IP ip: " + ip);
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                    //System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("X-Real-IP");
                    //System.out.println("X-Real-IP ip: " + ip);
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                    //System.out.println("getRemoteAddr ip: " + ip);
                }
                //获取本地ip
                if("0:0:0:0:0:0:0:1".equals(ip)){
                    try {
                        ip =  InetAddress.getLocalHost().getHostAddress();
                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //System.out.println("getLocal ip: " + ip);
                }
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateNowStr = sdf.format(d);
                logger.info("IP:" + ip+"\t时间:"+dateNowStr+"\t路径:" +request.getRequestURI());


            }
        });
        // 设置监听器的优先级
        bean.setOrder(0);

        return bean;
    }
}
