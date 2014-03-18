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
	
	static boolean firstTime = true;
	
	@Transactional
	public static void createDB() {
		
		if (firstTime) {
			JPA.em().persist(new Category(1, "Book", "fa-book"));
			JPA.em().persist(new Category(2, "Movie", "fa-film"));
			JPA.em().persist(new Category(3, "TV-Show", "fa-desktop"));
			
			JPA.em().persist(new Product(1, "Hobbit", "There and back again", "hobbit.jpg", 100, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 1),  JPA.em().find(Category.class, 2)))));	
			JPA.em().persist(new Product(2, "The Bro Code", "This book is aaaaawesome!", "bro-code.jpg", 210, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 1)))));	
			JPA.em().persist(new Product(3, "Iron Man 3", "Tinman goes wild", "iron-man-3.jpg", 320, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 2)))));	
			JPA.em().persist(new Product(4, "Thor 2", "By the power of Zues!", "thor-2.jpg", 430, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 2)))));
			JPA.em().persist(new Product(5, "True Blood", "It sucks", "true-blood.jpg", 420, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 1),  JPA.em().find(Category.class, 3)))));
			JPA.em().persist(new Product(6, "Doctor Who", "Wibbly wobbly timey wimey", "doctor-who.jpg", 430, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 3)))));
			JPA.em().persist(new Product(7, "Game of Thrones", "All men must die!", "song-of-ice-and-fire.jpg", 430, new LinkedList<Category>(Arrays.asList( JPA.em().find(Category.class, 1),  JPA.em().find(Category.class, 3)))));
			firstTime = false;
		}
	}
	
	public static Result index() {
		return ok(startpage.render("Your new application is ready."));
	}
	
	@Transactional
	public static Result setup() {
		createDB();
		return redirect("/");
	}
}
