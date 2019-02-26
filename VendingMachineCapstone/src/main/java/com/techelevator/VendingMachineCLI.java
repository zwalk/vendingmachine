package com.techelevator;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.techelevator.Exception.InventoryFileNotFoundException;
import com.techelevator.MoneyRunner.MoneyRunner;
import com.techelevator.Writer.AuditLogWriter;
import com.techelevator.Writer.SalesReportWriter;
import com.techelevator.inventory.Inventory;
import com.techelevator.inventory.Slot;
import com.techelevator.view.MainMenu;
import com.techelevator.view.MoneyFeedMenu;
import com.techelevator.view.PurchaseMenu;

public class VendingMachineCLI {

	private MainMenu mainMenu;
	private PurchaseMenu purchaseMenu; 
	private Inventory inventory;
	private MoneyFeedMenu moneyFeedMenu;
	private MoneyRunner moneyRunner;
	AuditLogWriter auditLogWriter;
	private SalesReportWriter salesReportWriter;
	List<String> userSlotChoices;
	
	
	public VendingMachineCLI(MainMenu mainMenu, PurchaseMenu purchaseMenu, MoneyFeedMenu moneyFeedMenu, MoneyRunner moneyRunner) throws InventoryFileNotFoundException, IOException {
		this.mainMenu = mainMenu;
		this.purchaseMenu = purchaseMenu;
		this.moneyFeedMenu = moneyFeedMenu;
		this.moneyRunner = moneyRunner;
		this.inventory = new Inventory();
		this.auditLogWriter = new AuditLogWriter();
	}
	
	public void run() throws IOException, InventoryFileNotFoundException {
		boolean fileCheck = loadInventoryCheck();

		this.salesReportWriter = new SalesReportWriter(inventory);
		
		
		while(fileCheck) {
			salesReportWriter.writeSalesReport();
			this.userSlotChoices = new ArrayList<String>();
			mainMenu.displayTitleBranding();
			String choice = (String)mainMenu.getChoiceFromOptions();
			
			if(choice.equals(mainMenu.getOptionDisplayItems())) {
				mainMenu.displayVendingMachine();
				mainMenu.displayVendingItems(inventory.getInventoryMap()); 
			} else if(choice.equals(mainMenu.getOptionPurchase())) {
				while (true) {
					
					String purchaseMenuChoice = (String)purchaseMenu.getChoiceFromOptions();
					
					if (purchaseMenuChoice.equals(purchaseMenu.getFeedOption())) {
					runMoneyFeedMenu();
					
					}
					
					if (purchaseMenuChoice.equals(purchaseMenu.getSelectProductOption())) {
					runSelectProductLogic();
					}
					
					if (purchaseMenuChoice.equals(purchaseMenu.getFinishTransactionOption())) {
					runFinishTransaction();
					break;
					}
				}
			}
		}
	}
	
	private void runFinishTransaction() {
		double balanceBeforeMakeChange = moneyRunner.getBalanceAsDouble();
		moneyRunner.makeChange();
		auditLogWriter.writeFinishTransactionLog(balanceBeforeMakeChange, moneyRunner.getBalanceAsDouble());
		purchaseMenu.displayMakeChangeMessage();
		
		for (String slotChoice : userSlotChoices) {
			purchaseMenu.displayProductMessages(inventory.getInventoryMap().get(slotChoice).getProduct());
		}
		
	}
	
	private void runSelectProductLogic() {
		String userSlotChoice = purchaseMenu.getSlotChoiceFromUser().toUpperCase();
		boolean isSlotChoiceValid = slotChoiceValidCheck(userSlotChoice);
		if (isSlotChoiceValid) {
			
			if (inventory.getInventoryMap().get(userSlotChoice).getProduct().getPriceInCents() > moneyRunner.getBalanceInCents()) {
				purchaseMenu.displayAdditionalBalanceNeededMessage();
			}
			
			if (inventory.getInventoryMap().get(userSlotChoice).getStock() == 0) {
				purchaseMenu.displaySoldOutMessage();
			}
			
			if (inventory.getInventoryMap().get(userSlotChoice).getProduct().getPriceInCents() <= moneyRunner.getBalanceInCents() && inventory.getInventoryMap().get(userSlotChoice).getStock() > 0) {
				runDispenseProcess(inventory.getInventoryMap().get(userSlotChoice), userSlotChoice);
			}
		}
	}
	
	private boolean slotChoiceValidCheck(String userSlotChoice) {
		boolean isSlotChoiceValid = inventory.getInventoryMap().containsKey(userSlotChoice);
		
		if (!isSlotChoiceValid) {
			purchaseMenu.displayInvalidChoiceMessage();
		}
		
		return isSlotChoiceValid;
	}
	
	private void runDispenseProcess(Slot slot, String userSlotChoice) {
		double balanceBeforeDispense = moneyRunner.getBalanceAsDouble();
		slot.dispenseProduct();
		userSlotChoices.add(userSlotChoice);
		moneyRunner.subtractBalance(slot.getProduct().getPriceInCents());
		auditLogWriter.writeSlotSelectLog(slot.getProduct().getName(), slot.getSlotName(), 
											balanceBeforeDispense, moneyRunner.getBalanceAsDouble());
	}
	
	private void runMoneyFeedMenu() {
		while (true) {
			int moneyFeedChoice = (int) moneyFeedMenu.getChoiceFromOptions(); 
			
			if (moneyFeedChoice != 0) {
				moneyRunner.addBalance(moneyFeedChoice);
				auditLogWriter.writeFeedMoneyLog((moneyFeedChoice / 100.0), moneyRunner.getBalanceAsDouble());
			} else {
				break;
			}
			
		}
	}
	
	private boolean loadInventoryCheck() {
		boolean fileCheck = true;
		try {
			inventory.loadInventory();
		}  catch (InventoryFileNotFoundException e) {
			mainMenu.showError(e);
			fileCheck = false;
			
		}
		
		return fileCheck;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, InventoryFileNotFoundException {
		InputStream in = System.in;
		OutputStream out = System.out;
		PrintWriter writer = new PrintWriter(out);
		Scanner reader = new Scanner(in);
		MainMenu mainMenu = new MainMenu(reader, writer);
		MoneyRunner moneyRunner = new MoneyRunner();
		PurchaseMenu purchaseMenu = new PurchaseMenu(reader, writer, moneyRunner);
		MoneyFeedMenu moneyFeedMenu = new MoneyFeedMenu(reader, writer, moneyRunner);
		VendingMachineCLI cli = new VendingMachineCLI(mainMenu, purchaseMenu, moneyFeedMenu, moneyRunner);
		cli.run();
	}
	
}
