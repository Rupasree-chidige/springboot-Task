package com.stackroute.track.config;


import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean=new ServletRegistrationBean(new WebdavServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}
