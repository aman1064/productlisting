package com.wingify.apiserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wingify.apiserver.filters.StatelessAuthenticationFilter;
import com.wingify.apiserver.service.impl.TokenAuthenticationService;
import com.wingify.apiserver.service.impl.UserService;



@Configuration
@EnableWebSecurity
@Order(100)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserService userService;
	private final TokenAuthenticationService tokenAuthenticationService;

	public SpringSecurityConfiguration() {
		super(true);
		this.userService = new UserService();
		tokenAuthenticationService = new TokenAuthenticationService("study-platform", userService);
	}

	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**","/images/**");
    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		http.anonymous()
			.and()
			.authorizeRequests()
			.antMatchers("/")
			.permitAll()
			.antMatchers("/favicon.ico")
			.permitAll()
			.antMatchers("**/*.html")
			.permitAll()
			.antMatchers("**/*.css")
			.permitAll()
			.antMatchers("/api/login")
			.permitAll()
			.antMatchers("/api/user/**")
			.permitAll()
			.antMatchers("**/*.js")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
					UsernamePasswordAuthenticationFilter.class);
			
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserService userDetailsService() {
		return userService;
	}

	@Bean
	public TokenAuthenticationService tokenAuthenticationService() {
		return tokenAuthenticationService;
	}
}
