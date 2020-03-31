package com.patroclos.oauth2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public void onLogoutSuccess(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Authentication authentication) 
					throws IOException, ServletException {

		String refererUrl = request.getHeader("Referer");
		logger.info("Logout from: " + refererUrl);
		OAuth2User principal = (OAuth2User) authentication.getPrincipal();
		logger.info("User logged out (username) : " + principal.getAttribute("name").toString());
		logger.info("User logged out (userid): " + principal.getAttribute("id").toString());
		
		super.onLogoutSuccess(request, response, authentication);
	}
}