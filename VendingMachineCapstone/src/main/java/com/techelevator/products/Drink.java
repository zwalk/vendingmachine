package com.techelevator.products;

public class Drink extends Product {

	public Drink(String name, double price) {
		super(name, price);
	}
	
	@Override
	public String getMessage() {
		return "Glug Glug, Yum!";
	}
	
}
