package com.employee_managment_system.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfigruation extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 //Disable CSRF
		   http=  http.csrf().disable();
		   
		   //To disable cooki base authetication 
		   http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
		   
		    http.authorizeRequests()
		    .antMatchers(HttpMethod.DELETE,"/employee/**").hasRole("ADMIN")
		    .antMatchers(HttpMethod.PUT,"/employee/**").hasRole("ADMIN")
		    .antMatchers(HttpMethod.GET,"/employee/**").permitAll()
		    .anyRequest().authenticated().and().httpBasic();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   PasswordEncoder encoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
	   auth.inMemoryAuthentication().withUser("prathamesh").password(encoder.encode("Pratham@206")).roles("USER")
	   .and().withUser("admin").password(encoder.encode("admin123")).roles("USER","ADMIN");
	}

}
