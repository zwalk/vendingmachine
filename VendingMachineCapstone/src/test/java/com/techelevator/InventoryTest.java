package com.techelevator;

import org.junit.*;
import com.techelevator.Exception.InventoryFileNotFoundException;
import com.techelevator.inventory.Inventory;


public class InventoryTest {
	private Inventory test;

	
	@Before
	public void setup() throws InventoryFileNotFoundException {
		test = new Inventory();
	}
	@Test
	public void initially_stocks_to_5() throws InventoryFileNotFoundException {
		test.loadInventory();
		Assert.assertEquals(5, test.getInventoryMap().get("A1").getStock());
		Assert.assertEquals(5, test.getInventoryMap().get("B2").getStock());
		Assert.assertEquals(5, test.getInventoryMap().get("C3").getStock());
		Assert.assertEquals(5, test.getInventoryMap().get("D4").getStock());
	}
	@Test
	public void slot_list_contains_accurate_slots() throws InventoryFileNotFoundException {
		test.loadInventory();
		Assert.assertEquals("A1", test.getInventoryMap().get("A1").getSlotName());
		Assert.assertEquals("B2", test.getInventoryMap().get("B2").getSlotName());
		Assert.assertEquals("C3", test.getInventoryMap().get("C3").getSlotName());
		Assert.assertEquals("D4", test.getInventoryMap().get("D4").getSlotName());
	}
}
