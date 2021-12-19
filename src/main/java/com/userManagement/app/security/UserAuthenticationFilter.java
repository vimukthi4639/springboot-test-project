package com.userManagement.app.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class UserAuthenticationFilter extends BasicAuthenticationFilter {

	public UserAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		//
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

//		String header = request.getHeader("Autherization");
//
//		if (header == null || !header.startsWith("bearer ")) {
//			chain.doFilter(request, response);
//			return;
//		}
//
//		String token = header.replace("bearer ", "");
//
//		String user = Jwts.parser().setSigningKey("VK43174hfahfdsfh892439").parseClaimsJws(token).getBody().getSubject();
//
//		if (user != null) {
//			SecurityContextHolder.getContext()
//					.setAuthentication(new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>()));
//
//
//		}
		chain.doFilter(request, response);

	}

}
