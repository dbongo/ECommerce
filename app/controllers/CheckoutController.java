package controllers;

import java.util.Date;

import models.ECOrder;
import models.User;
import play.*;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class CheckoutController extends Controller {

	@Transactional
    public static Result checkout() {
		if (session().containsKey("username")){
	    	User user = UserController.getCurrentUser();
	    	user.placeOrder();
	    	JPA.em().persist(user);
	        return ok(checkout.render("Check out or new checkout!"));
		} else {
			return redirect("/register");
		}
    }
}
