package com.bag.hotelmanagementsystem.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("index");
        registry.addViewController("/login/postlogin").setViewName("teacherShow");
        registry.addViewController("/login/postlogin").setViewName("studentShow");
        //registry.addViewController("/home").setViewName("userhome");
        registry.addViewController("/teacher/getTeacherPage").setViewName("teacherShow");
        registry.addViewController("/student/getStudentPage").setViewName("studentShow");
        registry.addViewController("/management/postStudentUpdate/{student_id}").setViewName("managementStudentUpdate");
        registry.addViewController("/management/getStudentInfoCreate/{student_id}").setViewName("managementStudentCreateInfo");
        //registry.addViewController("/403").setViewName("403");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converters.add(stringHttpMessageConverter);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

}