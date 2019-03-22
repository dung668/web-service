package edu.ptit.dao;

import java.util.List;

import edu.ptit.model.Product;
import edu.ptit.util.OrderBy;

public interface ProductDAO {
	// them
	Product addProduct(Product product);
	// sua
	void updateProduct(Product product);
	// xoa
	boolean removeProduct(int id);
	// tim kiem theo ten (va sap xep theo tieu chi nao do)
	List<Product> getProductsByName(String name, OrderBy orderBy);
	// lay tat ca san pham (va sap xep theo tieu chi nao do)
	List<Product> getAllProducts(OrderBy orderBy);
	// lay tat ca san pham theo category (va sap xep theo tieu chi nao do)
	List<Product> getLimitProductsByCategory(int categoryId, OrderBy orderBy, int pageId);
	// lay tat ca san pham theo keyword (va sap xep theo tieu chi nao do)
	List<Product> getLimitProductsByKeyWord(String keyword, OrderBy orderBy, int pageId);
	// tim kiem san pham voi so luong gioi han
	List<Product> getAllProductsLimit(OrderBy orderBy, int pageId);
	// dem so luong san pham
	int countProducts();
	// tim san pham theo id
	Product getProductById(int id);
	
}
