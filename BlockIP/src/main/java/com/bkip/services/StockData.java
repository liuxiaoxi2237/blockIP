package com.bkip.services;

import java.util.HashMap;
import java.util.List;

public class StockData {

	private String code;
	private String price;
	private String netrate;
	private String changerate;
	private String rank;
	public String getCode() {
		return code;
		
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNetrate() {
		return netrate;
	}
	public void setNetrate(String i) {
		this.netrate = i;
	}
	public String getChangerate() {
		return changerate;
	}
	public void setChangerate(String d) {
		this.changerate = d;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	

}
