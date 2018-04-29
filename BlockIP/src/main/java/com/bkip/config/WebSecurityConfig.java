package com.bkip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.bkip.authn.HerAuthenticationProvider;
import com.bkip.authn.HerInMemoryUserDetailsManager;


@Configuration
//enable web security
@EnableWebSecurity
//enable method security
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private HerAuthenticationProvider authProvider;
    
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        //disable CSRF
        .csrf().disable();
        //.csrf()
        //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
     http
        .authorizeRequests()        
        .anyRequest().authenticated()
        .and()
    .formLogin()
        .and()
    .logout()
        .permitAll()
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
        
    }

	//this is for customized authentication provider
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }	
    

    
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new HerInMemoryUserDetailsManager(user);
    }
    
    

    
}