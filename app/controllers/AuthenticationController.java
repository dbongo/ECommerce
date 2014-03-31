package controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import models.Category;
import models.Product;
import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class AuthenticationController extends Controller {
	
	@Transactional
	public static Result login() {
		
		Map <String, String[]> form = request().body().asFormUrlEncoded();
		
		String username = form.get("username")[0];
		String password = form.get("password")[0];

		boolean usernameIsEmpty = (username == null || username.equals(""));
		boolean passwordIsEmpty = (password == null || password.equals(""));
		
		if (usernameIsEmpty || passwordIsEmpty) {
			flash().put("fields-missing", "yes");
			return redirect("/");
		}
		
		TypedQuery<User> query = JPA.em().createQuery("SELECT u FROM User u WHERE email = :username AND password = :password", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<User> users = query.getResultList();
		if (users == null || users.size() == 0) {
			flash().put("icncorrect-information", "yes");
			return redirect("/");
		}
		
		session().put("username", username);
		
		return redirect("/");
	}
	
	public static Result logout() {
		session().clear();
		return redirect("/");
	}
	
}
