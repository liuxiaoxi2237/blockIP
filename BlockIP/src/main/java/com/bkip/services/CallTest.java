package com.bkip.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class CallTest {
	
	private String str01 = "HELLO, this is a method test function";
	private String str02 = "BYE, this is a method test function";	
	@PreAuthorize("hasRole('USER')")
	public String sayhello(){
		return str01;
	}
	
	@PreAuthorize("hasRole('USER')")
	public String saybye(){
		return str02;
	}
}
