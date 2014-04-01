package controllers;

import java.util.List;
import java.util.Map;

import models.Category;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.category;
import views.html.categories;

public class CategoryController extends Controller {

	@Transactional
    public static Result allCategories() {
        return ok(categories.render(getAllCategoriesFromRepo(), UserController.getCurrentUser()));
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
    @Security.Authenticated(MyAdminAuthenticator.class)
    public static Result newCategory() {
    	Map <String, String[]> form = request().body().asFormUrlEncoded();
    	
    	String name = form.get("name")[0];
    	String icon = "fa-" + form.get("icon")[0];
    	
    	Category category = new Category(name, icon);
    	
    	JPA.em().persist(category);
    	
    	return redirect("/categories");
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
