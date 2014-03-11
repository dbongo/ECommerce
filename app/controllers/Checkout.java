package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Checkout extends Controller {

    public static Result checkout() {
        return ok(checkout.render("Check out or new checkout!"));
    }

}
