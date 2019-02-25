package com.techelevator.inventory;

import com.techelevator.products.Product;

public class Slot {
	private String name;
	private int stock;
	private Product product;
	private int dispenseAmount = 0;
	
	public Slot(String name, Product product) {
		this.name = name;
		this.stock = 5;
		this.product = product;
	}
	
	public void dispenseProduct() {
		if (stock > 0) {
			stock--;
			dispenseAmount++;
		}
	}
	
	public String getSlotName() {
		return name;
	}
	
	public int getStock() {
		return stock;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public int getDispenseAmount() {
		return dispenseAmount;
	}
	
	public void resetDispenseAmount() {
		dispenseAmount = 0;
	}
	
}
