package com.bkip.mybatis;

import java.io.Serializable;

public class Person implements Serializable {
	
	private String Name;
	private int age;
	private int id;
	

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	@Override
	public String toString() {
		return getId() + "," + getName() + "," + getAge();
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

}
