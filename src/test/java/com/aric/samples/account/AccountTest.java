package com.aric.samples.account;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aric.samples.account.model.Account;
import java.lang.reflect.Field;


public class AccountTest {
	@Rule
    public ExpectedException exception = ExpectedException.none();
	
	public static final double balance=100D;
	public static final long id=4654654654L;
	public static final long ownerTckn=00000000000L;
	public static final String ownerFirstName="Okan";
	public static final String ownerLastName="Yildirim";
	
	@Test
	public void testAddingNegativeToBalance() {
		final Account account = new Account();
		exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Amount should be a positive value");        
        account.deposit(-1);
	}
	
	@Test
	public void testAddingZeroToBalance() {
		final Account account = new Account();
		exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Amount should be a positive value");        
        account.deposit(0);
	}
	
	@Test
	public void testAddingAmountToBalance() {
		final Account account = new Account();
		final double result = account.deposit(100);
        final double expected = 100;
        Assert.assertEquals(expected, result,0.0);
	}
	
	@Test
	public void testWithdrawNegativeFromBalance() {
		final Account account = new Account();
		exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Amount should be a positive value");        
        account.withdraw(-1);
	}
	
	@Test
	public void testWithdrawZeroFromBalance() {
		final Account account = new Account();
		exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Amount should be a positive value");        
        account.withdraw(0);
	}
	

	@Test
	public void testWithdrawGreaterAmountFromBalance() {
		final Account account = new Account();
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Amount cannot be greater than balance");
        account.withdraw(account.getBalance()/2d +1);
	}
	
	@Test
	public void testWithdrawAmountFromBalance() {
        final Account account = new Account();
        account.setBalance(41);
		final double result = account.withdraw(20);
        final double expected = 21;
        Assert.assertEquals(expected, result,0.0);
	}
	
	//get and set methods
	
	@Test
	public void testGetId()throws NoSuchFieldException, IllegalAccessException{
		final Account account=new Account();
		final Field field=account.getClass().getDeclaredField("id");
		field.setAccessible(true);
		field.set(account,id);
		assertEquals(field.get(account),id);
	}
	@Test
	public void testGetBalance()throws NoSuchFieldException, IllegalAccessException{
		final Account account=new Account();
		final Field field=account.getClass().getDeclaredField("balance");
		field.setAccessible(true);
		field.set(account,balance);
		assertEquals(field.get(account),balance);
	}
	@Test
	public void testGetOwnerTckn()throws NoSuchFieldException, IllegalAccessException{
		final Account account=new Account();
		final Field field=account.getClass().getDeclaredField("ownerTckn");
		field.setAccessible(true);
		field.set(account,ownerTckn);
		assertEquals(field.get(account),ownerTckn);
	}
	@Test
	public void testGetOwnerFirstName()throws NoSuchFieldException, IllegalAccessException{
		final Account account=new Account();
		final Field field=account.getClass().getDeclaredField("ownerFirstName");
		field.setAccessible(true);
		field.set(account,ownerFirstName);
		assertEquals(field.get(account),ownerFirstName);
	}
	@Test
	public void testGetOwnerLastName()throws NoSuchFieldException, IllegalAccessException{
		final Account account=new Account();
		final Field field=account.getClass().getDeclaredField("ownerLastName");
		field.setAccessible(true);
		field.set(account,ownerLastName);
		assertEquals(field.get(account),ownerLastName);
	}
	@Test
	public void testSetId()throws NoSuchFieldException, IllegalAccessException{
		final Account account=new Account();
		account.setId(id);
		final Field field=account.getClass().getDeclaredField("id");
		field.setAccessible(true);
		assertEquals(field.get(account),id);
	}

	@Test
	public void testSetBalance()throws NoSuchFieldException, IllegalAccessException{
		final Account account=new Account();
		account.setBalance(balance);
		final Field field=account.getClass().getDeclaredField("balance");
		field.setAccessible(true);
		assertEquals(field.get(account),balance);
	}
	@Test
	public void testSetOwnerTckn()throws NoSuchFieldException, IllegalAccessException{
		final Account account=new Account();
		account.setOwnerTckn(ownerTckn);
		final Field field=account.getClass().getDeclaredField("ownerTckn");
		field.setAccessible(true);
		assertEquals(field.get(account),ownerTckn);
	}
	@Test
	public void testSetOwnerFirstName()throws NoSuchFieldException, IllegalAccessException{
		final Account account=new Account();
		account.setOwnerFirstName(ownerFirstName);
		final Field field=account.getClass().getDeclaredField("ownerFirstName");
		field.setAccessible(true);
		assertEquals(field.get(account),ownerFirstName);
	}
	@Test
	public void testSetOwnerLastName()throws NoSuchFieldException, IllegalAccessException{
		final Account account=new Account();
		account.setOwnerLastName(ownerLastName);
		final Field field=account.getClass().getDeclaredField("ownerLastName");
		field.setAccessible(true);
		assertEquals(field.get(account),ownerLastName);
	}
	
}
