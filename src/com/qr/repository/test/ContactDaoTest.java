package com.qr.repository.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qr.repository.dao.AccountDao;
import com.qr.repository.dao.ContactDao;
import com.qr.repository.entity.Account;
import com.qr.repository.entity.Contact;

public class ContactDaoTest {

	AccountDao accountDao;
	ContactDao contactDao;
	Account account;
	Contact contact;
	@Before
	public void setUp() throws Exception {
		accountDao=new AccountDao();
		contactDao=new ContactDao();
		account=new Account();
		contact=new Contact();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testAddContact() {
		
		account.setName("GlobalRescue");	
		accountDao.addAccount(account);
		
		contact.setFirstName("Arif");
		contactDao.addContact(account, contact);
		
		contact=new Contact();
		contact.setFirstName("umer");
		contactDao.addContact(account, contact);
		
		List<Contact> contacts=contactDao.getAll(account.getId());
		assertEquals(2, contacts.size());								//test
		contactDao.deleteContact(account, contacts.get(0).getId());
		contactDao.deleteContact(account, contacts.get(1).getId());
		accountDao.deleteAccount(account.getId());
	}
	
	@Test
	public void testDeleteContact() {
		account.setName("GlobalRescue");	
		accountDao.addAccount(account);
		
		contact.setFirstName("Arif");
		contactDao.addContact(account, contact);
		
		contactDao.deleteContact(account, contact.getId());
		List<Contact> contacts=contactDao.getAll(account.getId());
		accountDao.deleteAccount(account.getId());
		assertEquals(0, contacts.size());								//test
	}
	
	/*@Test
	public void testGetAll() {
		account.setName("GlobalRescue");	
		accountDao.addAccount(account);
		
		List<Contact> contacts=contactDao.getAll(account.getId());
		assertEquals(0, contacts.size());								//test
		
		
		contact.setFirstName("Arif");
		contactDao.addContact(account, contact);
		contacts=contactDao.getAll(account.getId());
		assertEquals(1, contacts.size());								//test
		contactDao.deleteContact(account, contacts.get(0).getId());
		contacts=contactDao.getAll(account.getId());
		accountDao.deleteAccount(account.getId());
		assertEquals(0, contacts.size());								//test
	}
	*/
	
	
	@Test
	public void testGetAll() {
		
		account.setName("GlobalRescue");	
		accountDao.addAccount(account);
		
		List<Contact> contacts=contactDao.getAll(0);
		assertEquals(0, contacts.size());								//test
		
		contact.setFirstName("Arif");
		contactDao.addContact(account, contact);
		
		Account accountB=new Account();
		accountB.setName("Ptcl");	
		accountDao.addAccount(accountB);
		
		contact=new Contact();
		contact.setFirstName("Mehmood");
		contactDao.addContact(accountB, contact);
		

		contacts=contactDao.getAll(0);
		assertEquals(2, contacts.size());								//test
		
		accountDao.deleteAccount(account.getId());
		accountDao.deleteAccount(accountB.getId());
		
		contacts=contactDao.getAll(0);
		assertEquals(0, contacts.size());								//test
	}
	
	@Test
	public void testUpdateContact()
	{
		account.setName("GlobalRescue");
		accountDao.addAccount(account);
		
		contact.setFirstName("Arif");
		contactDao.addContact(account, contact);
		
		contact.setEmail("arif@gmail.com");
		contact.setLastName("Mehmood");
		contactDao.updateContact(contact);
		
		List<Contact> contacts= contactDao.getAll(account.getId());
		Contact contactC=contacts.get(0);

		assertEquals(contact.getLastName(),contactC.getLastName());			//test
		assertEquals(contact.getEmail(),contactC.getEmail());				//test
		contactDao.deleteContact(account, contactC.getId());
		accountDao.deleteAccount(account.getId());
	}
	

}
