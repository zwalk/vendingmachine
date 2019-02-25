package com.techelevator;

import org.junit.*;

import com.techelevator.products.Chip;

public class ChipTest {
	private Chip test;
	
	@Before
	public void setup() {
		test = new Chip("lays", 1.05d);
	}
	@Test
	public void get_message_returns_correct_message() {
		Assert.assertEquals("Crunch Crunch, Yum!", test.getMessage());
	}
	@Test
	public void get_name_returns_correct_name() {
		Assert.assertEquals("lays", test.getName());
	}
	@Test
	public void get_price_returns_correct_price() {
		Assert.assertEquals(1.05d, test.getPrice(), 0);
	}
}
