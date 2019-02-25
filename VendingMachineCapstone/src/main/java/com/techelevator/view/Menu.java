package com.techelevator.view;


import java.io.PrintWriter;
import java.util.Scanner;


public abstract class Menu {

	private PrintWriter out;
	private Scanner in;
	private Object[] options;
	
	public Menu(Scanner in, PrintWriter out) {
		this.out = out;
		this.in = in;
	}
	
	public Object getChoiceFromOptions() {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions();
			choice = getChoiceFromUserInput();
		}
		return choice;
	}

	protected Object getChoiceFromUserInput() {
		Object choice = null;
		String userInput = in.nextLine();
		out.println();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}

	protected void displayMenuOptions() {
		out.println();
		for(int i = 0; i < options.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+options[i]);
		}
		out.flush();
	}

	public void showError(Exception e) {
		out.println("OOPS! Something went wrong: " + e.getMessage());
		out.flush();
	}
	
	public String getInputFromUser(String message) {
		out.flush();
		out.print(message);
		out.flush();
		String input = in.nextLine();
		return input; 
	}
	
}