package com.bkip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.bkip.services.FunBean;

@Configuration
@ComponentScan(basePackages = "rest01.com.lg")
@PropertySource("classpath:a.properties")
public class AppConfig {
	
	@Value("${name}")
	private String fun01;
	
	@Bean
	public FunBean loadfunBean()
	{
		FunBean funBean = new FunBean();
		funBean.setFun01(fun01);
		return funBean;
	}
	
    @Bean  
    public static PropertySourcesPlaceholderConfigurer loadProperties() {  
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();  
        return configurer;  
    }
    


}