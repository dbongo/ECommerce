package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Products extends Controller {

    public static Result products() {
        return ok(products.render("Here be products! "));
    }

}
