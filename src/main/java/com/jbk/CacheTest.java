package com.jbk;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jbk.config.HibernateUtil;
import com.jbk.entity.Product;

public class CacheTest {

	public static void main(String[] args) {
		
SessionFactory sf=	HibernateUtil.getSessionFactory();
	
Session session1=	sf.openSession();
Session session2=	sf.openSession();

Product product= session1.get(Product.class, 1);
System.out.println(product);

Product product2= session2.get(Product.class, 1);
System.out.println(product2);

	}

}
