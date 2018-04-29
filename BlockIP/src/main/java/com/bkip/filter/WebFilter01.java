package com.bkip.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
//@WebFilter(filterName="filter01",urlPatterns={"/"})
public class WebFilter01 implements Filter {


	private FilterConfig filterConfig;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			System.out.println("-------------Run Before WebFilter01-------------");
            // Goes to default servlet
			chain.doFilter(request, response); 
			System.out.println("-------------Run After WebFilter01-------------");
	}
	
	public void destroy() {
		this.filterConfig = null;
	}


		

	}
