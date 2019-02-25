package com.techelevator.MoneyRunner;

import com.techelevator.Money.Dime;
import com.techelevator.Money.Money;
import com.techelevator.Money.Nickel;
import com.techelevator.Money.Quarter;

public class MoneyRunner {
	
	private int balance = 0;
	private Money[] changeValueArray = { new Quarter(), new Dime(), new Nickel() };
	private int quartersNeeded;
	private int dimesNeeded;
	private int nickelsNeeded;
	
	public void addBalance(int amount) {
		balance += amount;
	}
	
	public double getBalanceAsDouble() {
		double balanceAsDouble = balance / 100.00;
		return balanceAsDouble;
	}
	
	public int getBalanceInCents() {
		return balance;
	}
	
	public void subtractBalance(int amount) {
		balance -= amount;
	}
	
	public void makeChange() {
		quartersNeeded = 0;
		dimesNeeded = 0;
		nickelsNeeded = 0;
		
		for (Money money : changeValueArray) {
			int changeAmount = balance / money.getValue();
			
			if (changeAmount >= 1) {
				subtractBalance(changeAmount * money.getValue());
				
				if (money.getValue() == 25) {
					quartersNeeded = changeAmount;
				}
				
				if (money.getValue() == 10) {
					dimesNeeded = changeAmount;
				}
				
				if (money.getValue() == 5) {
					nickelsNeeded = changeAmount;
				}
			}
		} 
	}
	
	public int getQuartersNeeded() {
		return quartersNeeded;
	}
	
	public int getDimesNeeded() {
		return dimesNeeded;
	}
	
	public int getNickelsNeeded() {
		return nickelsNeeded;
	}
	
}
