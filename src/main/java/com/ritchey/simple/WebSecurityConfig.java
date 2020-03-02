package com.ritchey.simple;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import com.ritchey.ldap.MyUserDetailsContextMapper;

@Configuration
@PropertySource(value="file:build.properties", ignoreResourceNotFound=true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

	
	@Value("${ldap.bindUserDistinguishedName}")
	String bindUserDistingushedName;
	 
    @Value("${ldap.bindUserPassword}")
    private String bindUserPassword;
    
    @Value("${ldap.url}")
    String ldapUrl;
    
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/home").authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/greeting", true)
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

	protected GrantedAuthoritiesMapper getAuthoritiesMapper() throws Exception {
		SimpleAuthorityMapper simpleAuthorityMapper = new SimpleAuthorityMapper();
		simpleAuthorityMapper.setPrefix("ROLE_");
		simpleAuthorityMapper.afterPropertiesSet();
		return simpleAuthorityMapper;
	}
	
	  @Override
	  public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth
	      .ldapAuthentication()
	      	.userSearchBase("OU=LCU,DC=lcunet,DC=lcu,DC=edu")
	      	.userSearchFilter("(sAMAccountName={0})")
	        .userDnPatterns("uid={0},ou=people")
	        .groupSearchBase("CN=Users,DC=lcunet,DC=lcu,DC=edu")
	        .groupSearchFilter("(member={0})")
	        .groupRoleAttribute("name")
	        .authoritiesMapper(getAuthoritiesMapper())
	        .userDetailsContextMapper(userDetailsContextMaper())
	        .contextSource()
	        	.managerDn(bindUserDistingushedName)
	        	.managerPassword(bindUserPassword)
	          .url(ldapUrl)
	          
	          ;
	  }

	private UserDetailsContextMapper userDetailsContextMaper() {
		MyUserDetailsContextMapper ret = new MyUserDetailsContextMapper();
		ret.setUserAttributes(new String[]{"email","employeeId"});
		return ret;
	}
	  
}



