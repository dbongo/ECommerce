package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Cart extends Controller {

    public static Result cart() {
        return ok(cart.render("Go-cart-racing-page"));
    }

}
