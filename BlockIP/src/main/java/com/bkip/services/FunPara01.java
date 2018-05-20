package com.bkip.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("para01")
public class FunPara01 {
	@Value("bye, this is just a value test")
	private String ra01;

	public String getRa01() {
		return ra01;
		
	}

	public void setRa01(String ra01) {
		this.ra01 = ra01;
	}
	

}
