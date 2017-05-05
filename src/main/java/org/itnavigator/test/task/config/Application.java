package org.itnavigator.test.task.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
		registry.addResourceHandler("/static/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/static/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
