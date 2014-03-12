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
		put("1", new Product(1, "test", "testerer", 100,new LinkedList<Category>(Arrays.asList(new Category(1, "cat1")))));	
		put("2", new Product(2, "test2", "testerer2", 100,new LinkedList<Category>(Arrays.asList(new Category(2, "cat2")))));	
		put("3", new Product(3, "test3", "testerer3", 100,new LinkedList<Category>(Arrays.asList(new Category(1, "cat1"),new Category(2, "cat2")))));	
		put("4", new Product(4, "test4", "testerer4", 100,new LinkedList<Category>(Arrays.asList(new Category(1, "cat1"), new Category(2, "cat2")))));	
	}};
	

    public static Result allProducts() {
    	
        return ok(products.render(getAllProductsFromRepo()));
    }

    public static Result product(String id) {
        return ok(product.render("showing product with id: " + id));
    }

    private static List<Product> getAllProductsFromRepo() {
    	List<Product> allProducts;
    	allProducts = new LinkedList<>(productRepo.values());
    	return allProducts;
    }
  
    
}
