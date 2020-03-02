package com.ritchey.simple;


import java.util.Arrays;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan
@EnableWebMvc
@PropertySource(value="file:build.properties", ignoreResourceNotFound=true)
@MapperScan(basePackages = "com.ritchey.simple.mapper.chapel", sqlSessionTemplateRef="chapelSqlSessionTemplate")
@MapperScan(basePackages = "com.ritchey.simple.mapper.powercampus", sqlSessionTemplateRef="powercampusSqlSessionTemplate")
@SpringBootApplication(exclude={SolrAutoConfiguration.class})
public class SimpleApplication  {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationErrorListener.class);
    
    
   @Value("${database.chapel.driver}")
   String chapelDriver;
   
   @Value("${database.chapel.url}")
	private String databaseUrl;

   @Value("${database.chapel.user}")
	private String databaseUsername;

   @Value("${database.chapel.password}")
	private String databasePassword;
   
   @Value("${database.powerCampus.driver}")
   String campusDriver;
   
   @Value("${database.powerCampus.url}")
	private String campusUrl;

   @Value("${database.powerCampus.user}")
	private String campusUsername;

   @Value("${database.powerCampus.password}")
	private String campusPassword;
   
    
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
	   public ServletContextInitializer initializer() {
		   LOGGER.debug("SERVLET CONTEXT INIT");

	        return new ServletContextInitializer() {

	            @Override
	            public void onStartup(ServletContext servletContext) throws ServletException {
	                servletContext.setInitParameter("backgroundColor", "red");
	            }
	        };
	   }
	  
	   
		@Bean
		public TomcatServletWebServerFactory servletContainerFactory() {
		    return new TomcatServletWebServerFactory() {
		    	@Value("${ldap.bindUserDistinguishedName}")
		    	String bindUserDistingushedName;
		    	 
		        @Value("${ldap.bindUserPassword}")
		        private String bindUserPassword;
		        
		        @Value("${ldap.url}")
		        String ldapUrl;
		        
	    	    @Value("${database.chapel.driver}")
	    	    String chapelDriver;
	    	    
	    	    @Value("${database.chapel.url}")
				private String databaseUrl;

	    	    @Value("${database.chapel.user}")
				private String databaseUsername;

	    	    @Value("${database.chapel.password}")
				private String databasePassword;
	    	    
	    	    @Value("${database.powerCampus.driver}")
	    	    String campusDriver;
	    	    
	    	    @Value("${database.powerCampus.url}")
				private String campusUrl;

	    	    @Value("${database.powerCampus.user}")
				private String campusUsername;

	    	    @Value("${database.powerCampus.password}")
				private String campusPassword;

		    	@Override
		      protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
		    		LOGGER.debug("Tomcat web server builder");
		    		tomcat.enableNaming();
		    		TomcatWebServer ret = super.getTomcatWebServer(tomcat);
		    		return ret;
		      }
		    	
		    	@Override
		    	protected void postProcessContext(Context context) {
		    	    
		    		addResource(context, "jdbc/chapel", DataSource.class.getName(), chapelDriver, databaseUrl, databaseUsername, databasePassword);
		    		addResource(context, "jdbc/powerCampus", DataSource.class.getName(), campusDriver, campusUrl, campusUsername, campusPassword);
		    	}
		    	
		    	protected void addResource(Context context, String jndiName, String type, String driver, String url, String user, String password) {
		            ContextResource resource = new ContextResource();
		            resource.setName(jndiName);
		            resource.setType(type);
		            resource.setProperty("driverClassName", driver);
		            resource.setProperty("url", url);
		            resource.setProperty("password", password);
		            resource.setProperty("username", user);
		            context.getNamingResources().addResource(resource);
		    	}
		    
		    };
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
