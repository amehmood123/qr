package com.qr.repository.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.qr.repository.entity.Account;
import com.qr.repository.entity.Contact;
import com.qr.repository.factory.QrSessionFactory;

@SuppressWarnings("deprecation")
public class AccountDao {

	public List<Account> getAll() {

		List<Account> accounts = null;
		Session session = QrSessionFactory.startTransaction();
		String sql = "SELECT * FROM account";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Account.class);
		accounts = query.list();
		QrSessionFactory.endTransaction(session);
		return accounts;
	}

	public Boolean addAccount(Account account) {
		try {
			Session session = QrSessionFactory.startTransaction();
			session.save(account);
			QrSessionFactory.endTransaction(session);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Boolean deleteAccount(int id) {
		try {
			Session session = QrSessionFactory.startTransaction();
			Query q = session.createQuery("delete " + Contact.class.getName() + " where ACCOUNT_ID = " + id);
			q.executeUpdate();
			q = session.createQuery("delete " + Account.class.getName() + " where ID = " + id);
			q.executeUpdate();
			QrSessionFactory.endTransaction(session);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Boolean updateAccount(Account account){
		try {
			Session session = QrSessionFactory.startTransaction();
			session.evict(account);
			session.update(account);
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
