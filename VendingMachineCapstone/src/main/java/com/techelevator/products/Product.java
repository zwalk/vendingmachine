package com.techelevator.products;

public abstract class Product {
	private String name;
	private double price;
	private int priceInCents;
	
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
		this.priceInCents = (int)(price * 100);
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public abstract String getMessage();
	public int getPriceInCents() {
		return priceInCents;
	}
	
}
