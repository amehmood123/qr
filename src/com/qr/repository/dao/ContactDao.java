package com.qr.repository.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.qr.repository.entity.Account;
import com.qr.repository.entity.Contact;
import com.qr.repository.factory.QrSessionFactory;

@SuppressWarnings({ "deprecation", "unused" })
public class ContactDao {

	@SuppressWarnings({ "unchecked" })
	public List<Contact> getAll(int accountId) {
		
	List<Contact> contacts=null;
	Session session = QrSessionFactory.startTransaction();
	String sql;
	if(accountId!=0){
		 sql= "SELECT * FROM contact where ACCOUNT_ID="+accountId;
		}
	else{
		sql = "SELECT * FROM contact";
		}
	
	SQLQuery query = session.createSQLQuery(sql);
	query.addEntity(Contact.class);
	contacts = query.list();
	QrSessionFactory.endTransaction(session);
	return contacts;
	}
	
	public Boolean addContact(Account account,Contact contact)
	{
		try{
			Session session = QrSessionFactory.startTransaction();
			contact.setAccount(account);
			//account.getContacts().add(contact);
			session.save(contact);
			QrSessionFactory.endTransaction(session);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}

	public Boolean deleteContact(Account account, int contactId)
	{
		try {
			Session session = QrSessionFactory.startTransaction();
			Query q = session.createQuery("delete  " + Contact.class.getName() + "  where ID =" + contactId+" and ACCOUNT_ID="+account.getId());
			q.executeUpdate();
			QrSessionFactory.endTransaction(session);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Boolean updateContact(Contact contact){
		try {
			Session session = QrSessionFactory.startTransaction();
			session.evict(contact);
			session.update(contact);
			session.flush();
			session.clear();
			QrSessionFactory.endTransaction(session);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

