package com.jbk;

import java.util.List;

import com.jbk.entity.Product;
import com.jbk.operation.Operation;

public class Test {

	public static void main(String[] args) {
		
		Operation operation=new Operation();

		Product product = new Product("pen", 10, 10, "Stationary");
		//operation.saveProduct(product);
		
		
		Product product2=new Product(3, "pencile", 20, 10, "Stationary");
		
		//operation.updateProduct(product2);
		
		/*
		 * List<Product> list= operation.RestrictionEx();
		 * 
		 * for (Product product3 : list) { System.out.println(product3); }
		 */

		

	List<Product> list=	 operation.getListOfProductByName_Query("book");

for (Product product3 : list) {
	System.out.println(product3);
}

	}

}
