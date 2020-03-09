package com.ritchey.simple;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jndi.JndiTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import com.ritchey.ldap.MyUserDetailsContextMapper;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);
    
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/home").authenticated()
				.antMatchers("/greetingload").permitAll()
				.antMatchers("/rest").permitAll()
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

		  JndiTemplate template = new JndiTemplate();
		  String ldapUrl = template.lookup("java:comp/env/ldap/url", String.class);
		  String bindUserPassword = template.lookup("java:comp/env/ldap/bindUserPassword", String.class);
		  String bindUserDistingushedName = template.lookup("java:comp/env/ldap/bindUserDistinguishedName", String.class);
		  
		  LOGGER.debug("ldap url = " + ldapUrl);
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



