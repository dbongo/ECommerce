package controllers;

import java.util.LinkedList;
import java.util.List;

import models.Category;
import models.Product;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.product;
import views.html.products;

public class Products extends Controller {

	@Transactional
    public static Result allProducts() {
    	String category  = request().getQueryString("cat");
        return ok(products.render(getAllProductsFromRepo(category)));
    }

	@Transactional
    public static Result product(int id) {
    	Product prod = getProductFromRepo(id);
    	if (prod == null) {
    		return notFound("product not found");
    	}
        return ok(product.render(prod));
    }
    
	@Transactional
    private static List<Product> getAllProductsFromRepo(String category) {
    	List<Product> allProducts = JPA.em().createQuery("SELECT a FROM Product a", Product.class).getResultList();
    	if (category == null) {
    		return allProducts;
    	}
    	List<Product> filteredProducts = new LinkedList<>();
    	for (Product prod: allProducts) {
    		for (Category cat: prod.getCategories()) {
    			if (cat.getName().equals(category)) {
    				filteredProducts.add(prod);
    			}
    		}
    	}
    	return filteredProducts;
    }
	
	@Transactional    
    private static Product getProductFromRepo(int id) {
    	Product product = JPA.em().find(Product.class, id);
    	return product;
    }
    
}
