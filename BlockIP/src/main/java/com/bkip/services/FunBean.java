package com.bkip.services;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public class FunBean {
	
	
	private String fun01;
	public String getFun01() {
		return fun01;
	}
	public void setFun01(String fun01) {
		this.fun01 = fun01;
	}
	
	
	
	@Autowired
	public FunPara01 funPara01 = new FunPara01();
	public String getFun()
	{
		//return funPara01.getRa01();
		return fun01;
		
	}

}
