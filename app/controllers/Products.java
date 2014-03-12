package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import models.Category;
import models.Product;
import play.*;
import play.mvc.*;
import views.html.*;

public class Products extends Controller {

	private static Map<String, Product> productRepo = new HashMap<String, Product>(){{
		put("1", new Product(1, "Hobbit", "There and back again", 100, new LinkedList<Category>(Arrays.asList(new Category(1, "Book"), new Category(1, "Movie")))));	
		put("2", new Product(2, "The Bro Code", "This book is aaaaawesome!", 210, new LinkedList<Category>(Arrays.asList(new Category(2, "Book")))));	
		put("3", new Product(3, "Iron Man 3", "Tinman goes wild", 320, new LinkedList<Category>(Arrays.asList(new Category(1, "Movie")))));	
		put("4", new Product(4, "Thor 3", "By the power of Zues!", 430, new LinkedList<Category>(Arrays.asList(new Category(1, "Movie")))));	
	}};

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
    	allProducts = new LinkedList<>(productRepo.values());
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
    	if (productRepo.containsKey(id)) {
    		return productRepo.get(id);
    	} else {
    		return null;
    	}
    }
    
}
