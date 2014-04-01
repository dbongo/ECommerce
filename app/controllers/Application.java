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
import views.html.*;

public class Application extends Controller {
	
	
	@Transactional
	public static Result index() {
		return ok(startpage.render("<p>Happy browsing!</p>", UserController.getCurrentUser()));
	}
	
	@Transactional
	public static Result fourOFour(String requestedUri) {
		return notFound(fourOFour.render(request().path(), UserController.getCurrentUser()));
	}
}
