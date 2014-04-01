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
import play.mvc.Security;
import views.html.*;

public class OrderController extends Controller {
	
	@Transactional
	@Security.Authenticated(MyAuthenticator.class)
	public static Result orders() {
		return ok(orders.render(UserController.getCurrentUser()));
	}
	
}
