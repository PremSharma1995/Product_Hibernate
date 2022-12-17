package com.jbk.operation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jbk.config.HibernateUtil;
import com.jbk.entity.Product;

public class Operation {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void saveProduct(Product product) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction(); // save, update,delete

			session.save(product);
			transaction.commit();
			System.out.println("Product Saved");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Product getProductById(int productId) {
		Session session = sessionFactory.openSession();
		Product product = session.get(Product.class, productId); // select * from Product where productId= 1
		return product;
	}

	public void deleteProductById(int productId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction(); // save, update,delete

		Product product = session.get(Product.class, productId);
		session.delete(product);
		transaction.commit();
		System.out.println("Product Deleted ");
	}

	public void updateProduct(Product product) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction(); // save, update,delete

		Product prd = session.get(Product.class, product.getProductId());
		if (prd != null) {
			session.update(product); // work on primary column
			transaction.commit();
			System.out.println("Product Updated ");
		} else {
			System.out.println(" Product not found for update ");
		}

	}

	public List<Product> getProductList() {
		Session session = sessionFactory.openSession();
		List<Product> list = null;
		try {
			Criteria criteria = session.createCriteria(Product.class); // FROM Product

			criteria.setFirstResult(2); // pass index of column
			criteria.setMaxResults(2); // max record Pagination

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;

	}

	public List<Product> RestrictionEx() {
		Session session = sessionFactory.openSession();
		List<Product> list = null;
		try {
			Criteria criteria = session.createCriteria(Product.class); // FROM Product
			Criterion name = Restrictions.eq("productName", "book");
			Criterion price = Restrictions.eq("productPrice", 10);
			criteria.add(Restrictions.or(name, price));
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;

	}

	public List<Product> projectionEx() {
		Session session = sessionFactory.openSession();
		List<Product> list = null;
		double max = 0;
		try {
			Criteria criteria1 = session.createCriteria(Product.class);

			criteria1.setProjection(Projections.max("productPrice"));
			max = (Double) criteria1.list().get(0);

			Criteria criteria2 = session.createCriteria(Product.class);
			criteria2.add(Restrictions.eq("productPrice", max));
			list = criteria2.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;

	}

	public List<Product> getListOfProduct_Query() {
		Session session = sessionFactory.openSession();
		List<Product> list=null;
		try {
			String hql="FROM Product";
		Query<Product> query=	session.createQuery(hql);
		
	list=	query.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;

	}
	
	public List<Product> getListOfProductByName_Query(String productName) {
		Session session = sessionFactory.openSession();
		List<Product> list=null;
		try {
			String hql="FROM Product WHERE productName= :productName";   //Named Parameter
		Query<Product> query=	session.createQuery(hql);
		query.setParameter("productName", productName);
		
	list=	query.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;

	}

}
