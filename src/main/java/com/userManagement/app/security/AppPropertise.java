package com.userManagement.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppPropertise {

	@Autowired
	private Environment env;

	public String getTokenService() {
		return env.getProperty("spring.datasource.url");
	}

}
