package com.techelevator.products;

public class Gum extends Product {

	public Gum(String name, double price) {
		super(name, price);
	}
	
	@Override
	public String getMessage() {
		return "Chew Chew, Yum!";
	}
	
}
