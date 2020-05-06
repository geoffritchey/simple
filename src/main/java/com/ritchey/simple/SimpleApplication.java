package com.ritchey.simple;


import java.util.Arrays;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan
@MapperScan(basePackages = "com.ritchey.simple.mapper.chapel", sqlSessionTemplateRef="chapelSqlSessionTemplate")
@MapperScan(basePackages = "com.ritchey.simple.mapper.powercampus", sqlSessionTemplateRef="powercampusSqlSessionTemplate")
@SpringBootApplication(exclude={SolrAutoConfiguration.class})
public class SimpleApplication  {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationErrorListener.class);
    
	static public String version = "77777";

        
	public static void main(String[] args) throws NamingException {
		SpringApplication application = new SpringApplication(SimpleApplication.class);
		application.addListeners(new ApplicationPidFileWriter());
		application.addListeners(new ApplicationErrorListener());
		application.addListeners(new ApplicationShutdown());
		ApplicationContext ctx = application.run();

		System.err.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			LOGGER.info(beanName);
		}
		
	}
	
	@Bean
	public DataSource getCampusDatasource() throws NamingException {
	    JndiTemplate jndiTemplate = new JndiTemplate();
	    DataSource ds = null;
	    ds = (DataSource) jndiTemplate.lookup("java:comp/env/jdbc/powerCampus");
	    return ds;
	}

	
	@Bean
	@Primary
	public DataSource getChapelDatasource() throws NamingException {
	    JndiTemplate jndiTemplate = new JndiTemplate();
	    DataSource ds = null;
	    ds = (DataSource) jndiTemplate.lookup("java:comp/env/jdbc/chapel");
	    return ds;
	}
	
	@Bean
	public SqlSessionFactory chapelSqlSessionFactory() throws Exception {
	  SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	  factoryBean.setDataSource(getChapelDatasource());
	  return factoryBean.getObject();
	}
	
	@Bean
	@Primary
	public SqlSessionFactory powercampusSqlSessionFactory() throws Exception {
	  SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	  factoryBean.setDataSource(getCampusDatasource());
	  return factoryBean.getObject();
	}
	
	@Bean
	@Primary
	public SqlSessionTemplate chapelSqlSessionTemplate(@Qualifier("chapelSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
	    return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean
	@Primary
	public SqlSessionTemplate powercampusSqlSessionTemplate(@Qualifier("powercampusSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
	    return new SqlSessionTemplate(sqlSessionFactory);
	}
	
}
