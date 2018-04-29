package com.bkip.services;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FileReader {
	@Value("C:\\temp\\test1.txt")
	private String path;
	public StockData stockData = new StockData();
	ObjectMapper objectMapper = new ObjectMapper();
	
	@PreAuthorize("hasRole('USER')")
	public String readData() throws IOException{
	Path p1 = FileSystems.getDefault().getPath(path);
	List<String> lines = Files.readAllLines(p1);
	//space character to split
	String[] array = lines.get(0).split(" +");
	//Set data to bean
	stockData.setCode(array[0]);
	stockData.setPrice(array[1]);
	stockData.setNetrate(array[2]);
	stockData.setChangerate(array[3]);
	stockData.setRank(array[4]);

    // serialize to json
    String objectToJson = objectMapper.writeValueAsString(stockData);  
    //System.out.println(objectToJson); 
	
	return objectToJson;
	}
}
