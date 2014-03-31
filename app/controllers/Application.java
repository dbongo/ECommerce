package controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import models.Category;
import models.Product;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.startpage;

public class Application extends Controller {
	
	static boolean productsAdded = false;
	
//	@Transactional
//	public static void createDB() {
//		if (!productsAdded) {
//			JPA.em().persist(new Category("Book", "fa-book"));
//			JPA.em().persist(new Category("Movie", "fa-film"));
//			JPA.em().persist(new Category("TV-Show", "fa-desktop"));
//			
//			JPA.em().persist(new Product("Hobbit", "There and back again", "hobbit.jpg", 100, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 1),  JPA.em().find(Category.class, 2)))));	
//			JPA.em().persist(new Product("The Bro Code", "This book is aaaaawesome!", "bro-code.jpg", 210, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 1)))));	
//			JPA.em().persist(new Product("Iron Man 3", "Tinman goes wild", "iron-man-3.jpg", 320, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 2)))));	
//			JPA.em().persist(new Product("Thor 2", "By the power of Zues!", "thor-2.jpg", 430, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 2)))));
//			JPA.em().persist(new Product("True Blood", "It sucks", "true-blood.jpg", 420, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 1),  JPA.em().find(Category.class, 3)))));
//			JPA.em().persist(new Product("Doctor Who", "Wibbly wobbly timey wimey", "doctor-who.jpg", 430, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 3)))));
//			JPA.em().persist(new Product("Game of Thrones", "All men must die!", "song-of-ice-and-fire.jpg", 430, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 1),  JPA.em().find(Category.class, 3)))));
//			productsAdded = true;
//		}
//	}
	
	@Transactional
	public static Result index() {
//		if (productsAdded) {
//			if (request().getQueryString("login").equals("failed")) {
//				return ok(startpage.render(arg0))
//			}
		
			return ok(startpage.render("<p>Happy browsing!</p>", UserController.getCurrentUser()));
//		} else {
//			return ok(startpage.render("<p><a href='/setup'>Click me</a> to populate this site with content!</p>"));	
//		}
		
	}
	
//	@Transactional
//	public static Result setup() {
//		createDB();
//		return redirect("/");	
//	}
}
