package com.techelevator;

import com.techelevator.Exception.InventoryFileNotFoundException;
import com.techelevator.inventory.Slot;
import com.techelevator.reader.InventoryFileReader;

import java.util.Map;

import org.junit.*;

public class InventoryFileReaderTest {
	private InventoryFileReader test;
	
	@Before
	public void setup() {
		test = new InventoryFileReader("vendingmachine.csv");
	}
	@Test
	public void inventory_map_returns_correct_product_name() throws InventoryFileNotFoundException {
		Map <String, Slot> inventoryMap = test.createInventoryMap();	
		Assert.assertEquals("Potato Crisps", inventoryMap.get("A1").getProduct().getName());
		Assert.assertEquals("Stackers", inventoryMap.get("A2").getProduct().getName());
		Assert.assertEquals("Grain Waves", inventoryMap.get("A3").getProduct().getName());
		Assert.assertEquals("Cloud Popcorn", inventoryMap.get("A4").getProduct().getName());
		Assert.assertEquals("Moonpie", inventoryMap.get("B1").getProduct().getName());
		Assert.assertEquals("Cowtales", inventoryMap.get("B2").getProduct().getName());
		Assert.assertEquals("Wonka Bar", inventoryMap.get("B3").getProduct().getName());
		Assert.assertEquals("Crunchie", inventoryMap.get("B4").getProduct().getName());
		Assert.assertEquals("Cola", inventoryMap.get("C1").getProduct().getName());
		Assert.assertEquals("Dr. Salt", inventoryMap.get("C2").getProduct().getName());
		Assert.assertEquals("Mountain Melter", inventoryMap.get("C3").getProduct().getName());
		Assert.assertEquals("Heavy", inventoryMap.get("C4").getProduct().getName());
	}
	@Test
	public void inventory_map_returns_correct_price() throws InventoryFileNotFoundException {
		Map <String, Slot> inventoryMap = test.createInventoryMap();
		Assert.assertEquals(.85d, inventoryMap.get("D1").getProduct().getPrice(), 0);
		Assert.assertEquals(.95d, inventoryMap.get("D2").getProduct().getPrice(), 0);
		Assert.assertEquals(.75d, inventoryMap.get("D3").getProduct().getPrice(), 0);
		Assert.assertEquals(.75d, inventoryMap.get("D4").getProduct().getPrice(), 0);
	}
	@Test
	public void inventory_map_returns_correct_message() throws InventoryFileNotFoundException {
		Map<String, Slot> inventoryMap = test.createInventoryMap();
		Assert.assertEquals("Crunch Crunch, Yum!", inventoryMap.get("A1").getProduct().getMessage());
		Assert.assertEquals("Munch Munch, Yum!", inventoryMap.get("B2").getProduct().getMessage());
		Assert.assertEquals("Glug Glug, Yum!", inventoryMap.get("C3").getProduct().getMessage());
		Assert.assertEquals("Chew Chew, Yum!", inventoryMap.get("D4").getProduct().getMessage());
	}
}
