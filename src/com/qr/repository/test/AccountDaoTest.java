package com.qr.repository.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qr.repository.dao.AccountDao;
import com.qr.repository.entity.Account;

public class AccountDaoTest {

	AccountDao accountDao;
	Account grAccount;
	@Before
	public void setUp() throws Exception {
		accountDao = new AccountDao();
		grAccount = new Account();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetAll() {
		
		grAccount.setName("GlobalRescue");
		accountDao.addAccount(grAccount);
		
		List<Account> accounts = accountDao.getAll();
		Account account = accounts.get(accounts.size()-1);
		assertEquals(1, accounts.size());								//test
		
		assertEquals("GlobalRescue", account.getName());				//test
		
		accountDao.deleteAccount(account.getId());
		
		accounts = accountDao.getAll();
		assertEquals(0, accounts.size());								//test
		

		// return assertEquals(2, accountDao.getAll().size());

	}
	
	@Test
	public void testAddAccount() {
		
		List<Account> accounts = accountDao.getAll();
		int aSize=accounts.size();
		
		grAccount.setName("GlobalRescue");
		
		accountDao.addAccount(grAccount);
		accounts = accountDao.getAll();
		Account account = accounts.get(0);
		String nameA=account.getName();
		
		assertEquals(grAccount.getName(),nameA);						//test
		accountDao.deleteAccount(account.getId());
		accounts = accountDao.getAll();
		int bSize=accounts.size();
		assertEquals(aSize,bSize);										//test
	}

	@Test
	public void testDeleteAccount() {

		List<Account> accounts = accountDao.getAll();
		
		grAccount.setName("GlobalRescue");
		
		accountDao.addAccount(grAccount);
		accountDao.deleteAccount(grAccount.getId());
		
		List<Account> accountsB = accountDao.getAll();
		
		assertEquals(accounts.size(),accountsB.size());					//test
		
	}
	
	@Test
	public void testUpdateAccount()
	{
		grAccount.setName("GlobalRescue");
		accountDao.addAccount(grAccount);
		List<Account> accounts = accountDao.getAll();
		
		grAccount.setId(accounts.get(0).getId());
		grAccount.setCity("Islamabad");
		grAccount.setEmail("xyz@gmail.com");
		
		accountDao.updateAccount(grAccount);
		accounts = accountDao.getAll();
		
		Account account=accounts.get(accounts.size()-1);
		
		assertEquals(account.getCity(),grAccount.getCity());						//test
		assertEquals(account.getEmail(),grAccount.getEmail());						//test
		assertEquals(account.getName(),grAccount.getName());						//test
		accountDao.deleteAccount(account.getId());
		
	}
	

	
}
