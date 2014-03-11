package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Categories extends Controller {

    public static Result categories() {
        return ok(index.render("Look at this beautiful list of invisible categories"));
    }

}
