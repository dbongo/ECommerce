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

	private static List<Category> cats = new LinkedList<Category>(Arrays.asList(null, new Category(1, "Book", "fa-book"), new Category(2, "Movie", "fa-film"), new Category(3, "TV-Show", "fa-desktop")));
	
	private static Map<String, Product> productRepo = new HashMap<String, Product>(){{
		put("1", new Product(1, "Hobbit", "There and back again", "hobbit.jpg", 100, new LinkedList<Category>(Arrays.asList(cats.get(1), cats.get(2)))));	
		put("2", new Product(2, "The Bro Code", "This book is aaaaawesome!", "bro-code.jpg", 210, new LinkedList<Category>(Arrays.asList(cats.get(1)))));	
		put("3", new Product(3, "Iron Man 3", "Tinman goes wild", "iron-man-3.jpg", 320, new LinkedList<Category>(Arrays.asList(cats.get(2)))));	
		put("4", new Product(4, "Thor 2", "By the power of Zues!", "thor-2.jpg", 430, new LinkedList<Category>(Arrays.asList(cats.get(2)))));
		put("5", new Product(5, "True Blood", "It sucks", "true-blood.jpg", 420, new LinkedList<Category>(Arrays.asList(cats.get(1), cats.get(3)))));
		put("6", new Product(6, "Doctor Who", "Wibbly wobbly timey wimey", "doctor-who.jpg", 430, new LinkedList<Category>(Arrays.asList(cats.get(3)))));
		put("7", new Product(7, "Game of Thrones", "All men must die!", "song-of-ice-and-fire.jpg", 430, new LinkedList<Category>(Arrays.asList(cats.get(1), cats.get(3)))));
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
