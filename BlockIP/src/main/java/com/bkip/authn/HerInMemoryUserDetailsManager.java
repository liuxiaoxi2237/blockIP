package com.bkip.authn;

import java.util.Collection;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


public class HerInMemoryUserDetailsManager extends InMemoryUserDetailsManager{
    @Autowired
    private LoginAttemptService loginAttemptService;
  
    @Autowired
    private HttpServletRequest request;
    
    
	public HerInMemoryUserDetailsManager() {
		super();
	}

	public HerInMemoryUserDetailsManager(Collection<UserDetails> users) {
		super(users);
		
	}

	public HerInMemoryUserDetailsManager(UserDetails... users) {
		super(users);
		
	}

	public HerInMemoryUserDetailsManager(Properties users) {
		super(users);
	}
    
	private String getClientIP() {
	    String xfHeader = request.getHeader("X-Forwarded-For");
	    if (xfHeader == null){
	    	System.out.println(request.getRemoteAddr());
	        return request.getRemoteAddr();
	    }
	    System.out.println(xfHeader.split(",")[0]);
	    return xfHeader.split(",")[0];
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
        String ip = getClientIP();
        System.out.println("This is a test for blockip??????????????");
        if (loginAttemptService.isBlocked(ip)) {
        	System.out.println("This IP is blocked!!!!!!!!!!!!!!");
            throw new RuntimeException("blocked");
        }
        return super.loadUserByUsername(username);
		
	}

}
