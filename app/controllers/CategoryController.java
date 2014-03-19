package controllers;

import java.util.List;

import models.Category;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.category;
import views.html.categories;

public class CategoryController extends Controller {

	@Transactional
    public static Result allCategories() {
        return ok(categories.render(getAllCategoriesFromRepo()));
    }

    @Transactional
    public static Result category(Long id) {
    	Category cat = getCategoryFromRepo(id);
    	if (cat == null) {
    		return notFound("A category with that id was not found.");
    	}
        return ok(category.render(cat));
    }
    
    @Transactional
    private static List<Category> getAllCategoriesFromRepo() {
    	List<Category> allCategories = JPA.em().createQuery("SELECT a FROM Category a", Category.class).getResultList();	
    	return allCategories;
    }
    
    @Transactional
    private static Category getCategoryFromRepo(Long id) {
    	Category category = JPA.em().find(Category.class, id);
    	return category;
    }
    	
    
}
