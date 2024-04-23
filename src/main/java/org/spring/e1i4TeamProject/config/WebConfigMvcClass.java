package org.spring.e1i4TeamProject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfigMvcClass implements WebMvcConfigurer {

    String saveFiles = "file:///c:/e1i4_file/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/***")
            .addResourceLocations(saveFiles);
    }

}
