package com.vanny96.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;
import javax.validation.ValidatorFactory;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.vanny96")
@PropertySource("classpath:application.properties")
public class SpringContextConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver view = new InternalResourceViewResolver();
		
		view.setPrefix("/views/");
		view.setSuffix(".jsp");
		
		return view;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource data = new BasicDataSource();
		
		data.setDriverClassName("org.postgresql.Driver");
		data.setUrl(env.getProperty("jdbc.url"));
		data.setUsername(env.getProperty("jdbc.username"));
		data.setPassword(env.getProperty("jdbc.password"));
		
		return data;
	}
	
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		
		factory.setDataSource(dataSource());
		factory.setPackagesToScan("com.vanny96.model");
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		
		factory.setHibernateProperties(properties);
		
		try {
			factory.afterPropertiesSet();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return factory.getObject();
	}
	
	@Bean
	public HibernateTransactionManager txManager() {
		HibernateTransactionManager manager = new HibernateTransactionManager();
		
		manager.setSessionFactory(sessionFactory());
		manager.afterPropertiesSet();
				
		return manager;
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
