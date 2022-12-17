package com.jbk.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jbk.entity.Product;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
public static SessionFactory getSessionFactory() {
	Configuration cfg = new Configuration();
	cfg.configure().addAnnotatedClass(Product.class);

	sessionFactory = cfg.buildSessionFactory();
	
	return sessionFactory;
	
}
}
