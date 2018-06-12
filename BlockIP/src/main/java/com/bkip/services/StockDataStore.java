package com.bkip.services;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component 
public class StockDataStore {
	
	//be aware of this parameter!!!
	public static HashMap<String, StockData> stockDataMap; 
	
	@PostConstruct
	public void init()
	{
		stockDataMap = new HashMap();
	}
	
	public static void saveData(StockData stockData)
	{
		stockDataMap.put(stockData.getCode(), stockData);
	}

}
