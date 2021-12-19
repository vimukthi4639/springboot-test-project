package com.userManagement.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.userManagement.app.user.service.impl.UserServiceImpl;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserServiceImpl userService;
	private final BCryptPasswordEncoder bCrypterPassword;

	public WebSecurity(UserServiceImpl userService, BCryptPasswordEncoder encorder) {
		this.userService = userService;
		this.bCrypterPassword = encorder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/user/saveUser").permitAll().antMatchers("/h2-console/**").permitAll().anyRequest()
				.authenticated().and().addFilter(new AuthenticationFilter(authenticationManager()))
				.addFilter(new UserAuthenticationFilter(authenticationManager()));

		http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCrypterPassword);
	}

}
