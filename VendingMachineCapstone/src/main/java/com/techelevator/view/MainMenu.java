package com.techelevator.view;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
		
		List<Slot> slotList = new ArrayList<>();
		
		for (String slotName : inventoryMap.keySet()) {
			slotList.add(inventoryMap.get(slotName));
		}
		int productCount = slotList.size();
		int slotCount = 0;
		while (productCount != 0) {
			if (productCount >= 4) {
			out.print("\t---------------------------------------------------------------------------------------\n");
			out.printf("\t|  %-20s|%-20s|%-20s|%-20s|", slotList.get(slotCount).getProduct().getName(), 
						slotList.get(slotCount+1).getProduct().getName()
						,slotList.get(slotCount+2).getProduct().getName()
						,slotList.get(slotCount+3).getProduct().getName());
			out.println();
			out.printf("\t|  $%-19.2f|$%-19.2f|$%-19.2f|$%-19.2f|", slotList.get(slotCount).getProduct().getPrice(), 
					slotList.get(slotCount+1).getProduct().getPrice(),
					slotList.get(slotCount+2).getProduct().getPrice(),
					slotList.get(slotCount+3).getProduct().getPrice());
			out.println();
			String stock1 = slotList.get(slotCount).getStock() > 0 ? Integer.toString(slotList.get(slotCount).getStock()) + " Remaining" : "SOLD OUT";
			String stock2 = slotList.get(slotCount+1).getStock() > 0 ? Integer.toString(slotList.get(slotCount+1).getStock()) + " Remaining" : "SOLD OUT";
			String stock3 = slotList.get(slotCount+2).getStock() > 0 ? Integer.toString(slotList.get(slotCount+2).getStock()) + " Remaining" : "SOLD OUT";
			String stock4 = slotList.get(slotCount+3).getStock() > 0 ? Integer.toString(slotList.get(slotCount+3).getStock()) + " Remaining" : "SOLD OUT";
			out.printf("\t|  %-20s|%-20s|%-20s|%-20s|", stock1, stock2, stock3, stock4);
			out.println();
			out.printf("\t|  %-20s|%-20s|%-20s|%-20s|", slotList.get(slotCount).getSlotName(), slotList.get(slotCount+1).getSlotName(),
					slotList.get(slotCount+2).getSlotName(), slotList.get(slotCount+3).getSlotName());
			out.println();
			out.print("\t---------------------------------------------------------------------------------------");
			out.println();
			out.println();
			out.flush();
			slotCount += 4;
			productCount -= 4;
			}
			
			if (productCount == 3) {
				out.print("\t---------------------------------------------------------------------------------------\n");
				out.printf("\t|  %-20s|%-20s|%-20s|%-20s|", slotList.get(slotCount).getProduct().getName(), 
							slotList.get(slotCount+1).getProduct().getName()
							,slotList.get(slotCount+2).getProduct().getName()
							,"");
				out.println();
				out.printf("\t|  $%-19.2f|$%-19.2f|$%-19.2f| %-19.2s|", slotList.get(slotCount).getProduct().getPrice(), 
						slotList.get(slotCount+1).getProduct().getPrice(),
						slotList.get(slotCount+2).getProduct().getPrice(),
						"");
				out.println();
				String stock1 = slotList.get(slotCount).getStock() > 0 ? Integer.toString(slotList.get(slotCount).getStock()) + " Remaining" : "SOLD OUT";
				String stock2 = slotList.get(slotCount+1).getStock() > 0 ? Integer.toString(slotList.get(slotCount+1).getStock()) + " Remaining" : "SOLD OUT";
				String stock3 = slotList.get(slotCount+2).getStock() > 0 ? Integer.toString(slotList.get(slotCount+2).getStock()) + " Remaining" : "SOLD OUT";
				out.printf("\t|  %-20s|%-20s|%-20s|%-20s|", stock1, stock2, stock3, "");
				out.println();
				out.printf("\t|  %-20s|%-20s|%-20s|%-20s|", slotList.get(slotCount).getSlotName(), slotList.get(slotCount+1).getSlotName(),
						slotList.get(slotCount+2).getSlotName(), "");
				out.println();
				out.print("\t---------------------------------------------------------------------------------------");
				out.println();
				out.println();
				out.flush();
				slotCount += 3;
				productCount -= 3;
				}
			
			if (productCount == 2) {
					out.print("\t---------------------------------------------------------------------------------------\n");
					out.printf("\t|  %-20s|%-20s|%-20s|%-20s|", slotList.get(slotCount).getProduct().getName(), 
								slotList.get(slotCount+1).getProduct().getName()
								,""
								,"");
					out.println();
					out.printf("\t|  $%-19.2f|$%-19.2f| %-19.2s| %-19.2s|", slotList.get(slotCount).getProduct().getPrice(), 
							slotList.get(slotCount+1).getProduct().getPrice(),
							"",
							"");
					out.println();
					String stock1 = slotList.get(slotCount).getStock() > 0 ? Integer.toString(slotList.get(slotCount).getStock()) + " Remaining" : "SOLD OUT";
					String stock2 = slotList.get(slotCount+1).getStock() > 0 ? Integer.toString(slotList.get(slotCount+1).getStock()) + " Remaining" : "SOLD OUT";
					out.printf("\t|  %-20s|%-20s|%-20s|%-20s|", stock1, stock2, "", "");
					out.println();
					out.printf("\t|  %-20s|%-20s|%-20s|%-20s|", slotList.get(slotCount).getSlotName(), slotList.get(slotCount+1).getSlotName(), "", "");
					out.println();
					out.print("\t---------------------------------------------------------------------------------------");
					out.println();
					out.println();
					out.flush();
					
					slotCount += 2;
					productCount -= 2;
			}
			
			if (productCount == 1) {
				
					out.print("\t---------------------------------------------------------------------------------------\n");
					out.printf("\t|  %-20s|%-20s|%-20s|%-20s|", slotList.get(slotCount).getProduct().getName() 
								,""
								,""
								,"");
					out.println();
					out.printf("\t|  $%-19.2f| %-19.2s| %-19.2s| %-19.2s|", slotList.get(slotCount).getProduct().getPrice(), 
							"",
							"",
							"");
					out.println();
					String stock1 = slotList.get(slotCount).getStock() > 0 ? Integer.toString(slotList.get(slotCount).getStock()) + " Remaining" : "SOLD OUT";
					out.printf("\t|  %-20s|%-20s|%-20s|%-20s|", stock1, " ", " ", " ");
					out.println();
					out.printf("\t|  %-20s|%-20s|%-20s|%-20s|", slotList.get(slotCount).getSlotName(), "", "", "");
					out.println();
					out.print("\t---------------------------------------------------------------------------------------");
					out.println();
					out.println();
					out.flush();
					slotCount += 1;
					productCount -= 1;	
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
	
	public void displayTitleBranding() {
		out.println("\n" + 
				"███████╗ █████╗  ██████╗██╗  ██╗     █████╗ ███╗   ██╗██████╗     ██████╗ ███████╗███╗   ██╗    \n" + 
				"╚══███╔╝██╔══██╗██╔════╝██║  ██║    ██╔══██╗████╗  ██║██╔══██╗    ██╔══██╗██╔════╝████╗  ██║    \n" + 
				"  ███╔╝ ███████║██║     ███████║    ███████║██╔██╗ ██║██║  ██║    ██████╔╝█████╗  ██╔██╗ ██║    \n" + 
				" ███╔╝  ██╔══██║██║     ██╔══██║    ██╔══██║██║╚██╗██║██║  ██║    ██╔══██╗██╔══╝  ██║╚██╗██║    \n" + 
				"███████╗██║  ██║╚██████╗██║  ██║    ██║  ██║██║ ╚████║██████╔╝    ██████╔╝███████╗██║ ╚████║    \n" + 
				"╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝    ╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝     ╚═════╝ ╚══════╝╚═╝  ╚═══╝    \n" + 
				"                                                                                                \n" + 
				"                    ██╗   ██╗███████╗███╗   ██╗██████╗ ██╗███╗   ██╗ ██████╗                    \n" + 
				"                    ██║   ██║██╔════╝████╗  ██║██╔══██╗██║████╗  ██║██╔════╝                    \n" + 
				"                    ██║   ██║█████╗  ██╔██╗ ██║██║  ██║██║██╔██╗ ██║██║  ███╗                   \n" + 
				"                    ╚██╗ ██╔╝██╔══╝  ██║╚██╗██║██║  ██║██║██║╚██╗██║██║   ██║                   \n" + 
				"                     ╚████╔╝ ███████╗██║ ╚████║██████╔╝██║██║ ╚████║╚██████╔╝                   \n" + 
				"                      ╚═══╝  ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚═╝╚═╝  ╚═══╝ ╚═════╝                    \n" + 
				"                                                                                                \n");
		out.flush();
	}
	
	public void displayVendingMachine() {
//		List<String> slotList = new ArrayList<>();
//		
//		for (String slotName : inventoryMap.keySet()) {
//			slotList.add(slotName);
//		}
		
		out.println("\n\t\t\t    ____________________________________________\n"
				+ "\t\t\t   |############################################|\n" +
				"\t\t\t   |#|                           |##############|\n" +
				"\t\t\t   |#|  =====  ..--''`  |~~``|   |##|````````|##|\n" +
				"\t\t\t   |#|  |   |  \\     |  :    |   |##| Zach   |##|\n" +
				"\t\t\t   |#|  |___|   /___ |  | ___|   |##| & Ben  |##|\n" +
				"\t\t\t   |#|  /=__\\  ./.__\\   |/,__\\   |##| Vending|##|\n" +
				"\t\t\t   |#|  \\__//   \\__//    \\__//   |##|________|##|\n" +
				"\t\t\t   |#|===========================|##############|\n" +
				"\t\t\t   |#|```````````````````````````|##############|\n" +
				"\t\t\t   |#| =.._      +++     //////  |##############|\n" +
				"\t\t\t   |#| \\/  \\     | |     \\    \\  |#|`````````|##|\n" +
				"\t\t\t   |#|  \\___\\    |_|     /___ /  |#| _______ |##|\n" +
				"\t\t\t   |#|  / __\\\\  /|_|\\   // __\\   |#| |1|2|3| |##|\n" +
				"\t\t\t   |#|  \\__//-  \\|_//   -\\__//   |#| |4|5|6| |##|\n" +
				"\t\t\t   |#|===========================|#| |7|8|9| |##|\n" +
				"\t\t\t   |#|```````````````````````````|#| ``````` |##|\n" +
				"\t\t\t   |#| ..--    ______   .--._.   |#|[=======]|##|\n" +
				"\t\t\t   |#| \\   \\   |    |   |    |   |#|  _   _  |##|\n" +
				"\t\t\t   |#|  \\___\\  : ___:   | ___|   |#| ||| ( ) |##|\n" +
				"\t\t\t   |#|  / __\\  |/ __\\   // __\\   |#| |||  `  |##|\n" +
				"\t\t\t   |#|  \\__//   \\__//  /_\\__//   |#|  ~      |##|\n" +
				"\t\t\t   |#|===========================|#|_________|##|\n" +
				"\t\t\t   |#|```````````````````````````|##############|\n" +
				"\t\t\t   |############################################|\n" +
				"\t\t\t   |#|||||||||||||||||||||||||||||####```````###|\n" +
				"\t\t\t   |#||||||||||||PUSH|||||||||||||####\\|||||/###|\n" +
				"\t\t\t   |############################################|\n" +
				"\t\t\t    \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\///////////////////////\n" +
				 "\t\t\t    |________________________________|CR98|___|\n");
		out.println("\tArtwork by C.M. Rogers found at http://www.angelfire.com/co/cajhnesplace/ascii/vend.html");
		out.flush();
	}
}
