package com.socialmap.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by yy on 3/4/15.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.socialmap.server.controller")
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        // 将HTTP响应的Java类自动转换成JSON
        converters.add(new MappingJackson2HttpMessageConverter());
        // 自动转换字节数组，用于图片传输
        converters.add(new ByteArrayHttpMessageConverter());
    }
}
