package com.bkip.services;

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
	public void setNetrate(String netrate) {
		this.netrate = netrate;
	}
	public String getChangerate() {
		return changerate;
	}
	public void setChangerate(String changerate) {
		this.changerate = changerate;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}

}
