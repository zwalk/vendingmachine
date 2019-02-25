package com.techelevator.products;

public class Candy extends Product {

	public Candy(String name, double price) {
		super(name, price);
	}
	
	@Override
	public String getMessage() {
		return "Munch Munch, Yum!";
	}

}
