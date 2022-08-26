package com.stu.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;



@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.stu"})
public class StuDispatcher implements WebMvcConfigurer {

	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		
		return viewResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/reach/**").addResourceLocations("/extra/");
	}
	
	
	
	public DataSource source () {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/studentregister");
		ds.setUsername("root");
		ds.setPassword("saisithu112");
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		return ds;
	}
	
	@Bean
	public JdbcTemplate template () {
		return new JdbcTemplate(source());
	}
	
	
	
	
}
