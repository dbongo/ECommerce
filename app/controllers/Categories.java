package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Categories extends Controller {

    public static Result allCategories() {
    	
    	String str = request().getQueryString("category");
    	
    	if (str == null) {
    		str = "nope";
    	}
        return ok(categories.render(str));
    }

    public static Result category(String id) {
        return ok(category.render("Displaying category with id: " + id));
    }
}
