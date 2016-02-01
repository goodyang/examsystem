package com.goodyang.examsystem.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 使用HibernateSession工厂类来得到Session对象，用ThreadLocal来保存，
 * 可以达到每个线程拥有一个独立的Session对象的效果，以保证线程安全
 * @author Administrator
 *
 */
public class HibernateSessionFactory {
	private static String CONFIG_FILE_LOCATION 
					= "/hibernate.cfg.xml";
	private static final ThreadLocal<Session> threadLocal
					= new ThreadLocal<Session>();
	private static Configuration configuration
					= new Configuration();
	private static SessionFactory sessionFactory;
	private static String configFile = CONFIG_FILE_LOCATION;
	
	static {
		try {
			configuration.configure(configFile);
			sessionFactory = 
					configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.print("%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}
	
	private HibernateSessionFactory(){
	}
	
	public static Session getSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		if(session == null || !session.isOpen()) {
			if(sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null)?sessionFactory.openSession() : null;
			threadLocal.set(session);
		}
		return session;
	}
	
	public static void rebuildSessionFactory() {
		try {
			configuration.configure(configFile);
			sessionFactory = 
					configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.print("%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}
	
	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);
		if(session != null) {
			session.close();
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void setConfigFile(String configFile) {
		HibernateSessionFactory.configFile = configFile;
		sessionFactory = null;
	}
	
	public static Configuration getConfiguration() {
		return configuration;
	}
}



















