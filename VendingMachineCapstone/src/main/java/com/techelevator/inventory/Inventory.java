package com.techelevator.inventory;

import java.util.Map;
import com.techelevator.Exception.InventoryFileNotFoundException;
import com.techelevator.reader.InventoryFileReader;


public class Inventory {
	private Map<String, Slot> inventoryMap;
	private InventoryFileReader vendingFileRead = new InventoryFileReader("vendingmachine.csv");


	public void loadInventory() throws InventoryFileNotFoundException {
			this.inventoryMap = vendingFileRead.createInventoryMap();
	}
	
	public Map<String, Slot> getInventoryMap() {
		return inventoryMap;
	}

 }
