package com.bkip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bkip.mybatis.Person;
import com.bkip.mybatis.PersonDao;

@Component
public class RbacReader {
	

	@Autowired
	private PersonDao personDao;
	
	
	
	public String getPName(int id)
	{
		String abc = personDao.selectPersonById(id).getName();
		System.out.println("this is xxxxxxxxxx" + abc);
		return abc;
		
	}
}

