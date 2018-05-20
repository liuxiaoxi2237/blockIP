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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component("herAuthProvider")
public class HerAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserDetailsService herInMemoryUserDetailsManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public HerAuthenticationProvider()
	{
		super();
	}
	
	
	
	@Override
	 public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();
        //substring(8) to split cipher text from {bkrypt}xxxx
        String correctpassword = herInMemoryUserDetailsManager.loadUserByUsername(name).getPassword().substring(8);
        System.out.println("########################################password stored in DB is : " + correctpassword);
        System.out.println("########################################password present by user is : " + passwordEncoder.encode(password)); 
        //System.out.println("########################################password present by user chararry is : " + passwordEncoder.encode(password.toCharArray())); 

        /* bypass password verification*/
        if(passwordEncoder.matches(password, correctpassword))
        	{
            final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            final UserDetails principal = new User(name, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!username is: " + name);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!username is: " + password);
            return auth;
       /*bypass password verification*/     
        } else {
        	throw new BadCredentialsException("Really BadCredential");
        }
        
    }

	@Override
	public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
