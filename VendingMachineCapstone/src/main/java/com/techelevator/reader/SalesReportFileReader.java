package com.techelevator.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.Exception.InventoryFileNotFoundException;

public class SalesReportFileReader {
	private String filePath = "SalesReport.txt";
	
	public List<String> getSalesReportLines() throws InventoryFileNotFoundException {
		List<String> lines;
		
		try {
			lines = readFile();
		} catch (FileNotFoundException e) {
			throw new InventoryFileNotFoundException("File Not Found", e);
		}
		return lines;
		
	}
	
	private List<String> readFile() throws FileNotFoundException {
		List<String> lines = new ArrayList<String>();
		File salesFile = new File(filePath);
		
		try (Scanner reader = new Scanner(salesFile)) {
			
			while (reader.hasNextLine()) {
					lines.add(reader.nextLine());
			}
		}
		
		return lines;
	}
}
