package com.techelevator.Writer;


import java.util.List;
import com.techelevator.Exception.InventoryFileNotFoundException;
import com.techelevator.inventory.Inventory;
import com.techelevator.reader.SalesReportFileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class SalesReportWriter {
	PrintWriter salesReportWriter;
	File salesFile = new File("SalesReport.txt");
	Inventory inventory;
	SalesReportFileReader salesReportReader = new SalesReportFileReader();
	double existingRevenue = 0.0;
	double revenue = 0.0;
	
	public SalesReportWriter(Inventory inventory) throws IOException {
		this.inventory = inventory;
	}

	public void writeSalesReport() throws FileNotFoundException, InventoryFileNotFoundException {
			
		if (!salesFile.exists()) {
				writeInitialSalesReport();
		} else {
			List<String> salesReportLines = salesReportReader.getSalesReportLines();
			salesReportWriter = new PrintWriter(salesFile);
			updateSalesReport(salesReportLines);
		}
			addTotalSalesRevenueIntoReport();
	}
	
	private void writeInitialSalesReport() throws FileNotFoundException {
		salesReportWriter = new PrintWriter(salesFile);
		
		for (String slotName : inventory.getInventoryMap().keySet()) {
			salesReportWriter.println(inventory.getInventoryMap().get(slotName).getProduct().getName() + "|0");
			salesReportWriter.flush();
		}
	}
	
	private void updateSalesReport(List<String> salesReportLines) {
		boolean hasExistingRevenueCalculated = false;
		
		for (String slotName : inventory.getInventoryMap().keySet()) {
			boolean isProductAlreadyInReport = false;
			
			for (String line : salesReportLines) {
				
				String[] lineDetail = line.split("\\|");
				
				if (lineDetail[0].equals(inventory.getInventoryMap().get(slotName).getProduct().getName())) {
					updateSalesReportProductStillInInventory(slotName, lineDetail);
					isProductAlreadyInReport = true;
				}
				
				if (line.contains("TOTAL SALES") && hasExistingRevenueCalculated == false) {
					pullExistingRevenueFromSalesReport(line);
					hasExistingRevenueCalculated = true;
				}
			
			}
			
			if (!isProductAlreadyInReport) {
				writeInitialSalesLogForNewProduct(slotName);
			
			}
			
			
		}
		
		retainLegacyProductSales(salesReportLines);
		
	}
	
	private void updateSalesReportProductStillInInventory(String slotName, String[] lineDetail) {
		salesReportWriter.println(inventory.getInventoryMap().get(slotName).getProduct().getName() + "|" 
				+ (inventory.getInventoryMap().get(slotName).getDispenseAmount() + Integer.parseInt(lineDetail[1])));
		salesReportWriter.flush();
		revenue += inventory.getInventoryMap().get(slotName).getProduct().getPrice() * inventory.getInventoryMap().get(slotName).getDispenseAmount();
		inventory.getInventoryMap().get(slotName).resetDispenseAmount();
	}
	
	private void pullExistingRevenueFromSalesReport(String line) {
		String[] totalSalesDetail = line.split("\\$");
		existingRevenue += Double.parseDouble(totalSalesDetail[1]);
	}
	
	private void writeInitialSalesLogForNewProduct(String slotName) {
		salesReportWriter.println(inventory.getInventoryMap().get(slotName).getProduct().getName() + "|" 
				+ inventory.getInventoryMap().get(slotName).getDispenseAmount()); //will always be 0 because report is the first action of the application
			salesReportWriter.flush();
			inventory.getInventoryMap().get(slotName).resetDispenseAmount();
	}
	
	private void retainLegacyProductSales(List<String> salesReportLines) {
		for (String line : salesReportLines) {
			boolean isReportLineInInventory = false;
			
			if(line.contains("**TOTAL SALES**")) {
				break;
			}
			
			for (String slotName : inventory.getInventoryMap().keySet()) {
				String[] lineDetail = line.trim().split("\\|");
				if (lineDetail[0].equals(inventory.getInventoryMap().get(slotName).getProduct().getName())) {
					isReportLineInInventory = true;
				}
			}
		
			if (!isReportLineInInventory && !line.isEmpty()) {
			salesReportWriter.println(line);
			}
		}
	}
	
	private void addTotalSalesRevenueIntoReport() {
		double totalSalesRevenue = revenue + existingRevenue;
		revenue = 0.0;
		existingRevenue = 0.0;
		salesReportWriter.println("");
		salesReportWriter.flush();
		salesReportWriter.printf("**TOTAL SALES** $%,.2f", totalSalesRevenue);
		salesReportWriter.flush();
	}
}
