package com.nagarro.imagemanagement.daoimplementation;

import org.hibernate.query.Query;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.imagemanagement.dao.UserDao;
import com.nagarro.imagemanagement.model.User;
import com.nagarro.imagemanagement.utility.HibernateUtil;

/**
 * This UserDaoService class which implements the UserDao interface's methods
 * 
 * @author ashishaggarwal
 *
 */
public class UserDaoImpl implements UserDao {
	Logger log = Logger.getLogger(UserDaoImpl.class.getName());

	public boolean getUser(User loggedUser) {
		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			Transaction transaction = session.beginTransaction();
			String username = loggedUser.getUsername();
			String password = loggedUser.getPassword();
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(
					"from User u where u.username = '" + username + "' and u.password = '" + password + "'");
			User user = (User) query.uniqueResult();
			transaction.commit();
			if (user == null) {
				return false;
			}
		} catch (HibernateException e) {
			log.error(e.getMessage());
		}
		return true;
	}
}
