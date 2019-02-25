package com.techelevator.view;

import java.io.PrintWriter;
import java.util.Scanner;

import com.techelevator.MoneyRunner.MoneyRunner;

public class MoneyFeedMenu extends Menu {

	private Scanner in; 
	private PrintWriter out; 
	private final String ONE_DOLLAR = "$1";
	private final String TWO_DOLLAR = "$2"; 
	private final String FIVE_DOLLAR = "$5"; 
	private final String TEN_DOLLAR = "$10"; 
	private final String EXIT = "Exit"; 
	private final String[] FEED_MONEY_OUTPUT_CHOICE_ARRAY = {ONE_DOLLAR, TWO_DOLLAR, FIVE_DOLLAR, TEN_DOLLAR, EXIT};
	private final int ONE_DOLLAR_VALUE_IN_PENNIES = 100;
	private final int TWO_DOLLAR_VALUE_IN_PENNIES = 200; 
	private final int FIVE_DOLLAR_VALUE_IN_PENNIES = 500; 
	private final int TEN_DOLLAR_VALUE_IN_PENNIES = 1000; 
	private final int EXIT_VALUE = 0; 
	private final int[] FEED_MONEY_OPTIONS = {ONE_DOLLAR_VALUE_IN_PENNIES, TWO_DOLLAR_VALUE_IN_PENNIES,FIVE_DOLLAR_VALUE_IN_PENNIES,TEN_DOLLAR_VALUE_IN_PENNIES,EXIT_VALUE}; 
	private MoneyRunner moneyRunner;
	
	
	public MoneyFeedMenu(Scanner in, PrintWriter out, MoneyRunner moneyRunner) {
		super(in, out);
		this.moneyRunner = moneyRunner;
		this.in = in; 
		this.out = out; 
		
	}
	
	@Override
	protected void displayMenuOptions() {
		out.println();
		
		for(int i = 0; i < FEED_MONEY_OUTPUT_CHOICE_ARRAY.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+FEED_MONEY_OUTPUT_CHOICE_ARRAY[i]);
		}
		out.printf("\nCurrent Money Provided: $%-4.2f\n" , moneyRunner.getBalanceAsDouble());
		out.print("\nPlease choose amount you would like to feed or 5 to Exit >>>  ");
		
		
		out.flush();
	}
		
	@Override
	protected Object getChoiceFromUserInput() {
		Object choice = null;
		String userInput = in.nextLine();
		out.println();
		
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption > 0 && selectedOption <= FEED_MONEY_OPTIONS.length) {
				choice = FEED_MONEY_OPTIONS[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}

}
