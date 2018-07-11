package com.aric.samples.account.mocking;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Field;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import com.aric.samples.account.model.Account;
import com.aric.samples.account.repository.AccountRepository;
import com.aric.samples.account.service.AccountService;




import org.junit.Assert;

public class AccountServiceTest {
	 	private static List<Account> ListAccount;
	 	private static Account AcountName;
	 	private static Account AcountName2;
	    private static final String FIRST_NAME = "John";
	    private static final String LAST_NAME = "Smith";

	    @Rule
	    public ExpectedException exception = ExpectedException.none();
		
	    
	    @BeforeClass
	    public static void setUpBeforeClass() {
	    	ListAccount = new ArrayList<Account>();
	        AcountName = new Account();
	        AcountName.setOwnerFirstName(FIRST_NAME);
	        AcountName.setOwnerLastName(LAST_NAME);
	        AcountName.setId(1);
	        
	        AcountName2 = new Account();
	        AcountName2.setOwnerFirstName("Ken");
	        AcountName2.setOwnerLastName("Ben");
	        AcountName2.setId(2);
	        
	        ListAccount.add(AcountName);
	        ListAccount.add(AcountName2);
	    }
	    

	    @Autowired
	    private AccountService accountService;

	    @Before
	    public void setUp() {
	        final AccountRepository mockAccountRepository = Mockito.mock(AccountRepository.class);
	        accountService = new AccountService();
	        ReflectionTestUtils.setField(accountService, "accountRepository", mockAccountRepository);
	        Mockito.when(mockAccountRepository.findByOwnerFirstNameAndOwnerLastName(FIRST_NAME,LAST_NAME)).thenReturn(ListAccount);
	        Mockito.when(mockAccountRepository.findOne(ListAccount.get(0).getId())).thenReturn(ListAccount.get(0));
	        Mockito.when(mockAccountRepository.findOne(ListAccount.get(1).getId())).thenReturn(ListAccount.get(1));
	    }
	@Test
	public void testFindAccountsByFirstNameAndLastName() {
		
		final List<Account> serviceAcc = accountService.findAccountsByFirstNameAndLastName(FIRST_NAME,LAST_NAME);
		Assert.assertEquals(FIRST_NAME, serviceAcc.get(0).getOwnerFirstName());
		Assert.assertEquals(LAST_NAME, serviceAcc.get(0).getOwnerLastName());
	
	}
	
	 @Test
		public void testDepositeAccountService() {
		 	AcountName.setBalance(150);
		 	accountService.deposit(AcountName.getId(), (double) 50);
	        Assert.assertEquals(200, AcountName.getBalance(),0);
	        
		}
	 
	 @Test
		public void testDepositeNegativeAccountService() {
		 	AcountName.setBalance(150);
	        exception.expect(IllegalArgumentException.class);
	        exception.expectMessage("Amount should be a positive value"); 
	        accountService.deposit(AcountName.getId(), (double) -1);
		}
	 @Test
		public void testAddingZeroToBalance() {
			exception.expect(IllegalArgumentException.class);
	        exception.expectMessage("Amount should be a positive value");        
	        accountService.deposit(AcountName.getId(), (double) 0);
		}
	 
	 @Test
		public void testTransferingAmountFromBalance() {
		 	AcountName.setBalance(150);
	        AcountName2.setBalance(250);
	        accountService.transfer(AcountName.getId(), AcountName2.getId(), (double) 50);
	        Assert.assertEquals(100, AcountName.getBalance(),0);
	        Assert.assertEquals(350, AcountName2.getBalance(),0);
	        
		}
	 @Test
		public void testTransferingNegativeAmountFromBalance() {
		 	AcountName.setBalance(150);
	        AcountName2.setBalance(250);
	        exception.expect(IllegalArgumentException.class);
	        exception.expectMessage("Amount should be a positive value");
	        accountService.transfer(AcountName.getId(), AcountName2.getId(), (double) -1);
	       
		}

	 @Test
		public void testTransferingZeroAmountFromBalance() {
		 	AcountName.setBalance(150);
	        AcountName2.setBalance(250);
	        exception.expect(IllegalArgumentException.class);
	        exception.expectMessage("Amount should be a positive value");
	        accountService.transfer(AcountName.getId(), AcountName2.getId(), (double) 0);
	       
	        
		}
	 
	 @Test
		public void testTransferingGreaterAmountFromBalance() {
		 	AcountName.setBalance(150);
	        AcountName2.setBalance(250);
	        exception.expect(IllegalArgumentException.class);
	        exception.expectMessage("Amount cannot be greater than balance");
	        accountService.transfer(AcountName.getId(), AcountName2.getId(), (double) AcountName.getBalance()/2d +1);
	        
		}
	 
	 @Test
	   public void testDepositVerify(){
	      final AccountService accountService= Mockito.mock(AccountService.class);
	      accountService.deposit((long)1,50.0);
	      Mockito.verify(accountService).deposit((long)1,50.0);
	   }

	   @Test
	   public void testTransferVerify() {
	      final AccountService accountService = Mockito.mock(AccountService.class);
	      accountService.transfer(123L, 321L, 75.0);
	      Mockito.verify(accountService).transfer(123L, 321L, 75.0);
	   }

	   @Test
	   public void testFindAccountsByFirstNameAndLastNameVerify(){
	      final AccountService accountService = Mockito.mock(AccountService.class);
	      accountService.findAccountsByFirstNameAndLastName(FIRST_NAME,LAST_NAME);
	      Mockito.verify(accountService).findAccountsByFirstNameAndLastName(FIRST_NAME,LAST_NAME);
	   }
}
