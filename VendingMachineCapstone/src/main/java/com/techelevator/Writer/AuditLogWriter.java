package com.techelevator.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AuditLogWriter {

	private PrintWriter auditLogWriter;
	LocalDateTime dateTime = LocalDateTime.now().plusDays(1);
	DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
	private String formattedDate = dateTimeFormat.format(dateTime);

	public AuditLogWriter() throws IOException {
		auditLogWriter = new PrintWriter(new FileWriter(new File("Log.txt"), true));
	}
	
	public void writeFeedMoneyLog(double moneyFed, double balance) {
		auditLogWriter.printf("%-21s %-21s $%-6.2f      $%-4.2f\n", formattedDate, "FEED MONEY:", moneyFed, balance);
		auditLogWriter.flush();
	}
	
	public void writeSlotSelectLog(String productName, String slotName, double balanceBefore, double balanceAfter) {
		auditLogWriter.printf("%-21s %-18s %-2s $%-6.2f      $%-4.2f\n", formattedDate, productName, slotName, balanceBefore, balanceAfter);
		auditLogWriter.flush();
	}
	
	public void writeFinishTransactionLog(double balanceBefore, double balanceAfter) {
		auditLogWriter.printf("%-21s %-21s $%-6.2f      $%-4.2f\n", formattedDate, "GIVE CHANGE:", balanceBefore, balanceAfter);
		auditLogWriter.flush();	
	}
	
}
