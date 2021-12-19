package com.userManagement.app.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.management.RuntimeErrorException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userManagement.app.user.dto.UserLoginDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

		try {

			UserLoginDto cred = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(cred.getUserName(), cred.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		SecurityContextHolder.getContext().setAuthentication(authResult);

		String userName = ((UserDetails) authResult.getPrincipal()).getUsername();

		String token = Jwts.builder().setSubject(userName).setExpiration(new Date(System.currentTimeMillis() + 1000000))
				.signWith(SignatureAlgorithm.HS512, "VK43174hfahfdsfh892439").compact();

		response.addHeader("Autherization", "Bearer " + token);

	}
}
