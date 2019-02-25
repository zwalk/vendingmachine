package com.techelevator.view;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import com.techelevator.inventory.Slot;

public class MainMenu extends Menu{
	
	private PrintWriter out;
	private Scanner in;
	
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
			                               				MAIN_MENU_OPTION_PURCHASE };
	
	public MainMenu(Scanner in, PrintWriter out) {
		super(in, out);
		this.out = out;
		this.in = in;
	}
	
	public void displayVendingItems(Map<String, Slot> inventoryMap) {
		
		for (String slotName : inventoryMap.keySet()) {
			
			if (!(inventoryMap.get(slotName).getStock() == 0)) {
			out.printf("%s) %s \nPrice: $%-4.2f \n%s remaining.\n\n", inventoryMap.get(slotName).getSlotName(), 
						inventoryMap.get(slotName).getProduct().getName(), inventoryMap.get(slotName).getProduct().getPrice(),
						inventoryMap.get(slotName).getStock());
			} else {
				out.printf("%s) %s \nPrice: $%-4.2f \n%s \n\n", inventoryMap.get(slotName).getSlotName(), inventoryMap.get(slotName).getProduct().getName(), 
							inventoryMap.get(slotName).getProduct().getPrice(), "SOLD OUT");	
			}
		}

	}
	
	@Override
	protected void displayMenuOptions() {
		out.println();
		
		for(int i = 0; i < MAIN_MENU_OPTIONS.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+MAIN_MENU_OPTIONS[i]);
		}
		out.print("\nPlease choose an option >>>  ");
		out.flush();
		
	}
	
	@Override
	protected Object getChoiceFromUserInput() {
		Object choice = null;
		String userInput = in.nextLine();
		out.println();
	
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption > 0 && selectedOption <= MAIN_MENU_OPTIONS.length) {
				choice = MAIN_MENU_OPTIONS[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}
	
	public String getOptionDisplayItems() {
		return MAIN_MENU_OPTION_DISPLAY_ITEMS;
	}
	
	public String getOptionPurchase() {
		return MAIN_MENU_OPTION_PURCHASE;
	}
}
