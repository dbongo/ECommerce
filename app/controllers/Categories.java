package controllers;

import java.util.List;

import models.Category;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.categories;
import views.html.category;

import com.avaje.ebean.Ebean;

public class Categories extends Controller {
	
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
    	allCategories = Ebean.find(Category.class).findList();
    	return allCategories;
    }
    
    private static Category getCategoryFromRepo(String id) {
    	return Ebean.find(Category.class, id);
    }
    	
    
}
