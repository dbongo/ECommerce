package controllers;

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

public class Categories extends Controller {

	private static Map<String, Category> categoryRepo = new HashMap<String, Category>(){{
		put("1", new Category(1, "Book", "fa-book"));	
		put("2", new Category(2, "Movie", "fa-film"));
		put("3", new Category(2, "TV-Show", "fa-desktop"));	
	}};

	
    public static Result allCategories() {
        return ok(categories.render(getAllCategoriesFromRepo()));
    }

    public static Result category(String id) {
    	Category cat = getCategoryFromRepo(id);
    	if (cat == null) {
    		return notFound("A category with that id was not found.");
    	}
        return ok(category.render(cat));
    }
    
    private static List<Category> getAllCategoriesFromRepo() {
    	List<Category> allCategories;
    	allCategories = new LinkedList<>(categoryRepo.values());	
    	return allCategories;
    }
    
    private static Category getCategoryFromRepo(String id) {
    	if (categoryRepo.containsKey(id)) {
    		return categoryRepo.get(id);
    	} else {
    		return null;
    	}
    }
    	
    
}
