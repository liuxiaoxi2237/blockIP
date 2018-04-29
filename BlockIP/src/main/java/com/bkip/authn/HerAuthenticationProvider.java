package com.bkip.authn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component("authProvider")
public class HerAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserDetailsService herInMemoryUserDetailsManager;
	
	public HerAuthenticationProvider()
	{
		super();
	}
	
	@Override
	 public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();
        String correctpassword = herInMemoryUserDetailsManager.loadUserByUsername(name).getPassword();
        System.out.println("########################################password stored in DB is : " + correctpassword);
        
        if(password.equals(herInMemoryUserDetailsManager.loadUserByUsername(name).getPassword()))
        		{
        
        //if (name.equals("admin") && password.equals("system")) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            final UserDetails principal = new User(name, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!username is: " + name);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!username is: " + password);
            return auth;
        } else {
        	throw new BadCredentialsException("Really BadCredential");
        }
    }

	@Override
	public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
