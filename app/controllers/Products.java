package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Products extends Controller {

    public static Result allProducts() {
        return ok(products.render("Here be products! "));
    }

    public static Result product(String id) {
        return ok(products.render("showing product with id: " + id));
    }
    
}
