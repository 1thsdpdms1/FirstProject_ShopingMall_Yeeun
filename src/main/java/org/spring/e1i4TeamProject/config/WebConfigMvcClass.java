package org.spring.e1i4TeamProject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 스프링설정
public class WebConfigMvcClass implements WebMvcConfigurer {

  String saveFiles="file:///c:/e1i4_file/"; //실제 파일이 저장되는 경로
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/upload/***") // /upload/이미지명->가상
        .addResourceLocations(saveFiles); //실제 이미지 경로
  }
}
