package com.vanny96.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		
		auth.setPasswordEncoder(encoder);
		auth.setUserDetailsService(userDetailsService);
		
		return auth;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/posts/new").authenticated()
			.antMatchers("/*/{id}/delete").access("@idControlBean.checkId(authentication, #id)")
			.antMatchers("/*/{id}/edit").access("@idControlBean.checkId(authentication, #id)")
			.antMatchers("/**").permitAll()
		.and().formLogin()
			.loginProcessingUrl("/login")
			.permitAll()
		.and().logout()
			.logoutUrl("/Logout")
			.permitAll()
		.and()
			.exceptionHandling()
			.accessDeniedPage("/exceptions/access-denied");;
	}

	
}
