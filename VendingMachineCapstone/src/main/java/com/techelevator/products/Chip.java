package com.techelevator.products;

public class Chip extends Product {

	public Chip(String name, double price) {
		super(name, price);
	}
	
	@Override
	public String getMessage() { 
		return "Crunch Crunch, Yum!";
	}
	
}
