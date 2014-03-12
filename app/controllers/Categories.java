package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Categories extends Controller {

    public static Result allCategories() {
        return ok(categories.render("Look at this beautiful list of invisible categories"));
    }

    public static Result category(String id) {
        return ok(category.render("Displaying category with id: " + id));
    }
}
