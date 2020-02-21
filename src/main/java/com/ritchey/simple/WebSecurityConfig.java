package com.ritchey.simple;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.ritchey.ldap.MyUserDetailsContextMapper;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/home").authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	protected GrantedAuthoritiesMapper getAuthoritiesMapper() throws Exception {
		SimpleAuthorityMapper simpleAuthorityMapper = new SimpleAuthorityMapper();
		simpleAuthorityMapper.setPrefix("ROLE_");
		simpleAuthorityMapper.afterPropertiesSet();
		return simpleAuthorityMapper;
	}
	
	  @Override
	  public void configure(AuthenticationManagerBuilder auth) throws Exception {
		  String ldapUrl = "ldap://tyr.lcunet.lcu.edu:389/";
		  String bindUserDistingushedName = "CN=spamuser,CN=Users,DC=lcunet,DC=lcu,DC=edu";
		  String bindUserPassword = "B@rracuda123";

	
		
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



