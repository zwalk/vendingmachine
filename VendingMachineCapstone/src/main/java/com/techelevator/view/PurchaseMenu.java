package com.techelevator.view;

import java.io.PrintWriter;
import java.util.Scanner;

import com.techelevator.MoneyRunner.MoneyRunner;
import com.techelevator.products.Product;

public class PurchaseMenu extends Menu {
	private PrintWriter out;
	private Scanner in;
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money"; 
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product"; 
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction"; 
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, 
															PURCHASE_MENU_OPTION_SELECT_PRODUCT,
															PURCHASE_MENU_OPTION_FINISH_TRANSACTION};
	private MoneyRunner moneyRunner; 
	
	public PurchaseMenu(Scanner in, PrintWriter out, MoneyRunner moneyRunner) {
		super(in, out);
		this.moneyRunner = moneyRunner; 
		this.out = out;
		this.in = in;
	}
	
	@Override
	protected void displayMenuOptions() {
		out.println();
		out.flush();
		
		for(int i = 0; i < PURCHASE_MENU_OPTIONS.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+PURCHASE_MENU_OPTIONS[i]);
		}
		out.printf("\nCurrent Money Provided: $%-4.2f", moneyRunner.getBalanceAsDouble());
		out.flush();
		out.print("\nPlease choose an option >>>  ");
		out.flush();
		
	}
	
	@Override
	protected Object getChoiceFromUserInput() {
		Object choice = null;
		String userInput = in.nextLine();
		out.println();
		out.flush();
		
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption > 0 && selectedOption <= PURCHASE_MENU_OPTIONS.length) {
				choice = PURCHASE_MENU_OPTIONS[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}
	
	public void displayAdditionalBalanceNeededMessage() {
		out.println("\nAdditional Funds Needed: Please feed money or choose new product within your current balance.");
		out.flush();
	}
	
	public void displayMakeChangeMessage() {
		out.println("Your change is " + moneyRunner.getQuartersNeeded() + " quarters, " + moneyRunner.getDimesNeeded()
																+ " dimes, and " + moneyRunner.getNickelsNeeded() + " nickels.");
		out.flush();
	}
	
	public void displayProductMessages(Product product) {
		out.println(product.getMessage());
		out.flush();
	}
	
	public void displaySoldOutMessage() {
		out.println("\nSorry, that product is sold out. Please choose again.");
		out.flush();
	}
	
	public String getSlotChoiceFromUser() {
		out.print("Please enter the slot number of the product >>>  ");
		out.flush();
		String input = in.nextLine();
		return input; 
	}
	
	public void displayInvalidChoiceMessage() {
		out.println("\nInvalid slot selection. Please choose again.");
		out.flush();
	}
	
	public String getFeedOption() {
		return PURCHASE_MENU_OPTION_FEED_MONEY;
	}
	
	public String getSelectProductOption() {
		return PURCHASE_MENU_OPTION_SELECT_PRODUCT;
	}
	
	public String getFinishTransactionOption() {
		return PURCHASE_MENU_OPTION_FINISH_TRANSACTION;
	}
	
}
