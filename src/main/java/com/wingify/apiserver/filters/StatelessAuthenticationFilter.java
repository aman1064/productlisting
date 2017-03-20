package com.wingify.apiserver.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.wingify.apiserver.service.impl.TokenAuthenticationService;

@Component
public class StatelessAuthenticationFilter extends GenericFilterBean {

	private final TokenAuthenticationService authenticationService;

	public StatelessAuthenticationFilter(TokenAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	/*	if (!httpRequest.getRequestURI().matches("/api/login") && !httpRequest.getRequestURI().matches("/api/user/*")
				&& !httpRequest.getRequestURI().contains("swagger")
				&& !httpRequest.getRequestURI().contains("configuration")
				&& !httpRequest.getRequestURI().contains("webjars")
				&& !httpRequest.getRequestURI().contains("api-docs")
				&& !httpRequest.getRequestURI().contains("images")
				) {*/
			Authentication authentication = authenticationService.getAuthentication(httpRequest);
			if (authentication != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			//}
		}
		filterChain.doFilter(request, response);
		SecurityContextHolder.getContext().setAuthentication(null);
	}
}
