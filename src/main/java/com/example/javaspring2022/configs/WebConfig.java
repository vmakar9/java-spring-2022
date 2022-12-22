package com.example.javaspring2022.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String folder = System.getProperty("user.home") + File.separator + "Pictures"+File.separator;
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///"+folder);
    }
}
