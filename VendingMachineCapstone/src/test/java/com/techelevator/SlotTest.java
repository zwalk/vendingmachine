package com.techelevator;

import org.junit.*;

import com.techelevator.inventory.Slot;
import com.techelevator.products.Chip;

public class SlotTest {
	private Slot test;
	Chip testChip;
	
	@Before
	public void setup() {
		testChip = new Chip("lays", 1.50d);
		test = new Slot("A1", testChip);
	}
	@Test
	public void get_slot_name_returns_correct_slot_name() {
		Assert.assertEquals("A1", test.getSlotName());
	}
	@Test
	public void get_stock_returns_correct_stock_at_initial_stock() {
		Assert.assertEquals(5, test.getStock());
	}
	@Test
	public void dispense_stock_removes_correct_stock_amount() {
		test.dispenseProduct();
		Assert.assertEquals(4, test.getStock());
	}
	@Test
	public void get_product_returns_correct_product() {
		Assert.assertEquals(testChip, test.getProduct());
	}
}
