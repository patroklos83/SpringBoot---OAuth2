package com.patroclos.oauth2;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import com.patroclos.oauth2.CustomLogoutSuccessHandler;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

@EnableWebSecurity
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests(a -> a
				.antMatchers("/", "/error", "/webjars/**").permitAll()
				.anyRequest().authenticated()
				)
		.exceptionHandling(e -> e 
				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				) 
		.csrf(c -> c
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				)
		.logout(l -> l
				.logoutSuccessUrl("/")
				.logoutSuccessHandler(logoutSuccessHandler())
				.permitAll()
				)
		.oauth2Login()
		.defaultSuccessUrl("/")
		.failureUrl("/loginFailure");
	}


	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}


}
