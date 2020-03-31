package com.patroclos.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

@RestController
@SpringBootApplication
@ComponentScan("com.patroclos")
public class TemplateApplication{

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@GetMapping("/user")
	public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
		logger.info("User logged in (username) : " + principal.getAttribute("name").toString());
		logger.info("User logged in (userid): " + principal.getAttribute("id").toString());
		return Collections.singletonMap("name", principal.getAttribute("name"));
	}	 


	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
	}



	public void run(String... args) throws Exception {

	}







}
