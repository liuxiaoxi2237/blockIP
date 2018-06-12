package com.bkip.controller;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//Request Paramater
import org.springframework.web.bind.annotation.RequestParam;

import com.bkip.services.CallTest;
import com.bkip.services.FileReader;
import com.bkip.services.RbacReader;
import com.bkip.services.StockData;
import com.bkip.services.StockDataStore;




@RestController
@CrossOrigin
//class or method level CORS configuration not work!
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
    
    //@CrossOrigin
    @RequestMapping(value="/hello",method={RequestMethod.GET,RequestMethod.POST})
    public StockData sayhello(HttpSession session,HttpServletResponse response,HttpServletResponse request) 
    {	
    		StockData stockData = new StockData();
    		stockData.setChangerate("1.5");
    		stockData.setCode("600030");
    		stockData.setNetrate("25.0");
    		stockData.setPrice("63.6");
    		stockData.setRank("1");
    	
    		return stockData;
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
    
    @RequestMapping(value="/writeData",method={RequestMethod.GET,RequestMethod.POST})
     public void writeData(@RequestBody StockData stockData)
     {
     	System.out.println("Write data as below *************************************************");    	
    	System.out.println("The code is:" + stockData.getCode());
    	System.out.println("The changerate is:" + stockData.getChangerate());
    	System.out.println("The price is:" + stockData.getPrice());
    	System.out.println("The netrate is:" + stockData.getNetrate());
    	System.out.println("The rank is:" + stockData.getRank());
     	System.out.println("Write data as above***************************************************");
     	StockDataStore.saveData(stockData);

     }
    
    @RequestMapping(value="/readData",method={RequestMethod.GET})
    //Remember the @RequestParam instead of @QueryParam
	public StockData readData(@RequestParam("code") String code)
	{
    	StockData stockData = StockDataStore.stockDataMap.get(code);
    	System.out.println("ReadData as below *************************************************");
    	System.out.println("The code is:" + stockData.getCode());
    	System.out.println("The changerate is:" + stockData.getChangerate());
    	System.out.println("The price is:" + stockData.getPrice());
    	System.out.println("The netrate is:" + stockData.getNetrate());
    	System.out.println("The rank is:" + stockData.getRank());
     	System.out.println("ReadData as above *************************************************");
    	return stockData;
	
	}
}

