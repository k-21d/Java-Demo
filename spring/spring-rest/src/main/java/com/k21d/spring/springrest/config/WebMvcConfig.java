package com.k21d.spring.springrest.config;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.k21d.spring.springrest.http.message.PropertiesPersonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.smile.MappingJackson2SmileHttpMessageConverter;

import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.List;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new PropertiesPersonHttpMessageConverter());
    }
}
