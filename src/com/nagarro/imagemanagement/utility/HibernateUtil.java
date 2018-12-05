package com.nagarro.imagemanagement.utility;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;
/**
 * This HibernateUtil class helps to set the session factory which configures the models 
 * @author ashishaggarwal
 *
 */
public class HibernateUtil {
	static Logger log = Logger.getLogger(HibernateUtil.class.getName());
	private static SessionFactory sessionFactory;
	static {
		try {
			
			sessionFactory = new Configuration().configure()
					.addPackage("com.nagarro.imagemanagement.model")
					.addAnnotatedClass(User.class)
					.addAnnotatedClass(Image.class)
					.buildSessionFactory();
		} catch (HibernateException ex) {
			log.error("Initial SessionFactory creation failed." + ex.getMessage());
			
		}
	}

	public static SessionFactory getSessionFactory() {	
		return sessionFactory;
		
	}

	private HibernateUtil() {
		super();
	}
}
