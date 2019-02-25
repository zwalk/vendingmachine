package com.techelevator.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.techelevator.Exception.InventoryFileNotFoundException;
import com.techelevator.inventory.Slot;
import com.techelevator.products.Candy;
import com.techelevator.products.Chip;
import com.techelevator.products.Drink;
import com.techelevator.products.Gum;

public class InventoryFileReader {
	private String filePath;
	
	public InventoryFileReader(String filePath) {
		this.filePath = filePath;
	}
	
	public Map<String, Slot> createInventoryMap() throws InventoryFileNotFoundException {
		List<String> lines;
		
		try {
			lines = readFile();
		} catch (FileNotFoundException e) {
			throw new InventoryFileNotFoundException("File Not Found", e);
		}
		return buildInventoryMap(lines);
		
	}
	
	private List<String> readFile() throws FileNotFoundException {
		List<String> lines = new ArrayList<String>();
		File vendingFile = new File(filePath);
		
		try (Scanner reader = new Scanner(vendingFile)) {
			while (reader.hasNextLine()) {
				lines.add(reader.nextLine());
			}
		}
		
		return lines;
	}
	
	private Map<String, Slot> buildInventoryMap(List<String> lines) {
		Map<String, Slot> inventoryMap = new LinkedHashMap<String, Slot>();
		
		for (String line : lines) {
			
			if (line == null) {
				continue;
			}
			
			String[] productDetails = line.split("\\|");
			
			if (productDetails[3].equals("Chip")) {
				inventoryMap.put(productDetails[0], new Slot(productDetails[0], new Chip(productDetails[1],Double.parseDouble(productDetails[2]))));
			} 
			
			if (productDetails[3].equals("Candy")) {
				inventoryMap.put(productDetails[0], new Slot(productDetails[0], new Candy(productDetails[1],Double.parseDouble(productDetails[2]))));
			} 
			
			if (productDetails[3].equals("Gum")) {
				inventoryMap.put(productDetails[0], new Slot(productDetails[0], new Gum(productDetails[1],Double.parseDouble(productDetails[2]))));
			} 
			
			if (productDetails[3].equals("Drink")) {
				inventoryMap.put(productDetails[0], new Slot(productDetails[0], new Drink(productDetails[1],Double.parseDouble(productDetails[2]))));
			}
		}
	
		return inventoryMap;
	}
}
