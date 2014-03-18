package controllers;

import java.util.Arrays;
import java.util.LinkedList;

import models.Category;
import models.Product;

import com.avaje.ebean.Ebean;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	private static boolean productsAdded = false;
	
	private static void createDB() {
		
		if (!productsAdded) {
			Ebean.save(new Category(1, "Book", "fa-book"));
			Ebean.save(new Category(2, "Movie", "fa-film"));
			Ebean.save(new Category(3, "TV-Show", "fa-desktop"));
		
			Ebean.save(new Product(1, "Hobbit", "There and back again", "hobbit.jpg", 100, new LinkedList<Category>(Arrays.asList(Ebean.find(Category.class, 1), Ebean.find(Category.class, 2)))));	
			Ebean.save(new Product(2, "The Bro Code", "This book is aaaaawesome!", "bro-code.jpg", 210, new LinkedList<Category>(Arrays.asList(Ebean.find(Category.class, 1)))));	
			Ebean.save(new Product(3, "Iron Man 3", "Tinman goes wild", "iron-man-3.jpg", 320, new LinkedList<Category>(Arrays.asList(Ebean.find(Category.class, 2)))));	
			Ebean.save(new Product(4, "Thor 2", "By the power of Zues!", "thor-2.jpg", 430, new LinkedList<Category>(Arrays.asList(Ebean.find(Category.class, 2)))));
			Ebean.save(new Product(5, "True Blood", "It sucks", "true-blood.jpg", 420, new LinkedList<Category>(Arrays.asList(Ebean.find(Category.class, 1), Ebean.find(Category.class, 3)))));
			Ebean.save(new Product(6, "Doctor Who", "Wibbly wobbly timey wimey", "doctor-who.jpg", 430, new LinkedList<Category>(Arrays.asList(Ebean.find(Category.class, 3)))));
			Ebean.save(new Product(7, "Game of Thrones", "All men must die!", "song-of-ice-and-fire.jpg", 430, new LinkedList<Category>(Arrays.asList(Ebean.find(Category.class, 1), Ebean.find(Category.class, 3)))));
			productsAdded = true;	
		}
	}
	
	public static Result index() {
		if (!productsAdded) {
			return ok(startpage.render("<p><a href='/setup'>Klick me</a> to add some content to the site!</p>"));	
		} else {
			return ok(startpage.render("Happy browsing!"));
		}
		
	}
	
	public static Result setup() {
		if (!productsAdded) {
			createDB();
		}
		return redirect("/");
		
		
	}
}
