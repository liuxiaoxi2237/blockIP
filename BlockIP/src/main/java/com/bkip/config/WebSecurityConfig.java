package com.bkip.config;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.bkip.authn.HerAuthenticationProvider;
import com.bkip.authn.HerInMemoryUserDetailsManager;


@Configuration
//enable web security
@EnableWebSecurity
//enable method security
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private HerAuthenticationProvider herAuthProvider;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors() //CORS configuration
        .and()
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
    	
        auth.authenticationProvider(herAuthProvider);
    }	
    
    //CORS configuration
    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
    	CorsConfiguration configuration = new CorsConfiguration();
    	//This is important if authentication required
    	configuration.setAllowCredentials(true);
    	configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		//!!!This is very important for CORS with authentication header!!!
		configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		//System.out.println("Below is cors configuration:");
/*		
		Map<String, CorsConfiguration> conn = source.getCorsConfigurations();
		Iterator it = conn.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
			System.out.println(pair.getKey() + "=" + pair.getValue());
			it.remove();
		}
*/
		
    	return source;
    }
    
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password( "password")
                .roles("USER")
                .build();

        return new HerInMemoryUserDetailsManager(user);
        //return new InMemoryUserDetailsManager(user);
    }
   
    

    
}