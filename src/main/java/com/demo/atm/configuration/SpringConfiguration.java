package com.demo.atm.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SpringConfiguration extends WebSecurityConfigurerAdapter
{
	
	
	 
	        
//	
//	@Override
//	configure

}
