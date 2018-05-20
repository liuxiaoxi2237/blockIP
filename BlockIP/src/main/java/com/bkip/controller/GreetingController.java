package com.bkip.controller;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bkip.services.CallTest;
import com.bkip.services.FileReader;
import com.bkip.services.RbacReader;




@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    @Autowired
    private ApplicationContext appContext;
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private FileReader fileReader;
    @Autowired
    private RbacReader rbacReader;
    
    @Autowired
    private CallTest callTest;

    //@Autowired
    //private FunBean funBean;
    
    /*
    public FilterChainProxy getFilter()
    {
    	return appContext.getBean(FilterChainProxy.class);
    }
	
	FilterChainProxy filterChainProxy = this.getFilter();
	*/
    
    @RequestMapping(value="/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name,HttpSession session,HttpServletResponse response,HttpServletResponse request) 
    {
        //return new Greeting(counter.incrementAndGet(),String.format(template, name));
    	//return funBean.getFun();
    	
    	//cookie configuration
    	response.addHeader("Set-Cookie", "username=LiuGang; HttpOnly; Secure; SameSite=Lax");
    	response.addHeader("Set-Cookie", "password=Emsee123!; HttpOnly; Secure; SameSite=Strict");
    	//session configuration
    	session.setAttribute("skey01", "sname01");
    	String sname = (String) session.getAttribute("skey01");
    	
    	/* this used to handle filterChainProxy, still not work!!!
    	List<Filter> filterList = filterChainProxy.getFilters("/");
    	for(Filter filter : filterList)
    	{
    	System.out.println("The filter is: " + filter.getClass().getName().toString());
    	
    	}  
    	*/ 	
    	
    	//This is used to test servlet API integration with spring security
    	
    	
    	
    	return sname;
    	//Get container application context and get bean loaded in container
    	//return appContext.getBeanDefinitionNames()[1];
    }
    
    
    @RequestMapping(value="/hello")
    public String sayhello(@RequestParam(value="name", defaultValue="World") String name,HttpSession session,HttpServletResponse response,HttpServletResponse request) 
    {
    	
    		return callTest.sayhello();
    	}
    @RequestMapping(value="/bye")
    public String saybye(@RequestParam(value="name", defaultValue="World") String name,HttpSession session,HttpServletResponse response,HttpServletResponse request) 
    {
    	
    		return callTest.saybye();
    	}
    
    @RequestMapping(value="/getData",method=RequestMethod.GET)
    public String getData() throws IOException 
    {
    		return fileReader.readData();
    	}
    
    @RequestMapping(value="/rbacGetName")
    public String rbacGetName() throws IOException
    {
    	return  rbacReader.getPName(2);
    	
    }
}

