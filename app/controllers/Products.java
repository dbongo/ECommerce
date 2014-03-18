package controllers;

import java.util.LinkedList;
import java.util.List;

import models.Category;
import models.Product;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.product;
import views.html.products;

import com.avaje.ebean.Ebean;

public class Products extends Controller {
	

    public static Result allProducts() {
    	String category  = request().getQueryString("cat");
        return ok(products.render(getAllProductsFromRepo(category)));
    }

    public static Result product(String id) {
    	Product prod = getProductFromRepo(id);
    	if (prod == null) {
    		return notFound("product not found");
    	}
        return ok(product.render(prod));
    }
    

    private static List<Product> getAllProductsFromRepo(String category) {
    	List<Product> allProducts;
    	allProducts = Ebean.find(Product.class).findList();
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
    
    private static Product getProductFromRepo(String id) {
    	return Ebean.find(Product.class, id);
    }
    
}
