package controllers;

import java.util.List;

import models.Category;
import models.Product;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class AdminController extends Controller { 
	
	public static Result start() {
		return ok(admin.render("YAY!"));
	}
	
	@Transactional
	public static Result productForm() {
		List<Category> categories =  JPA.em().createQuery("SELECT c FROM Category c", Category.class).getResultList();
		return ok(productForm.render(categories));
	}
	
	public static Result categoryForm() {
		return ok(categoryForm.render());
	}
	
	
}
