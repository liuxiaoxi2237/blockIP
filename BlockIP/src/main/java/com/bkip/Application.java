package com.bkip;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bkip.mybatis.PersonDao;
import com.bkip.mybatis.PersonMapper;
import com.bkip.services.StockData;

@SpringBootApplication
public class Application implements CommandLineRunner{
		
    //Get container 
	//@Autowired
    //private ApplicationContext appContext;

	
	    public static void main(String[] args) 
	    {
        SpringApplication.run(Application.class, args);
        
	    }
/*	    
	    @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedOrigins("*");
	            }
	        };
	    }	    
*/	    
	    
	    public void run(String... args) throws Exception{
	    	
	    	BCryptPasswordEncoder passcoder = new BCryptPasswordEncoder();
	    	String pass01 = passcoder.encode("123456");
	    	String pass02 = passcoder.encode("123456");
	    	System.out.println("####################encrypted password is 1:" + pass01);
	    	//System.out.println("####################encrypted password is 2:" + passcoder.encode("password"));
	    	System.out.println("the pass01 result is:"  + passcoder.matches("123456", pass01));
	    	System.out.println("the pass01 result is:"  + passcoder.matches("123456", pass02));
	    	
	    }
/*	    
	    @Autowired
	    private PersonMapper personMapper;
	    
	    @Autowired
	    private PersonDao personDao;
	    
		@Override
		public void run(String... args) throws Exception {
			System.out.println("****************************************************************************************");
			System.out.println(this.personMapper.selectbyId(1));
			System.out.println(this.personDao.selectPersonById(2));
		}
		*/
	    
/*	    
    public void run(String... args) throws Exception {

        String[] beans = appContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        for (String bean : beans) {
            System.out.println(bean);
        	}
    }
    */
}