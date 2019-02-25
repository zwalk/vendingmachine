package com.techelevator;




import org.junit.*;

import com.techelevator.MoneyRunner.MoneyRunner; 


public class MoneyRunnerTest {
	
	private MoneyRunner test; 
	
	@Before
	public void setup() {
		
	test = new MoneyRunner(); 	
	}
	
	@Test
	public void add_balance_updates_balance_by_correct_increment() {
		
		test.addBalance(500);
		Assert.assertEquals(5, test.getBalanceAsDouble(), 0);
		
		
	}
	@Test
	public void subtract_balance_updates_balance_by_correct_increment() {
		
		test.addBalance(500);
		test.subtractBalance(100);
		Assert.assertEquals(4, test.getBalanceAsDouble(), 0);
	}
	
	@Test
	public void make_change_calculates_correct_changes() {
		
		test.addBalance(115);
		test.makeChange();
		
		Assert.assertEquals(4, test.getQuartersNeeded());
		Assert.assertEquals(1, test.getDimesNeeded());
		Assert.assertEquals(1, test.getNickelsNeeded());

	}

}
