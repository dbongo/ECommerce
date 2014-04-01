package controllers;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.Product;
import models.User;
import models.UserStatus;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Security;
import views.html.*;
import play.mvc.Result;



public class UserController extends Controller {
		
	@Transactional
	public static User getCurrentUser() {
		String username = session().get("username");
		if (username == null) {
			flash().put("no-current-user", "yes");
			return null;
		}
		TypedQuery<User> query = JPA.em().createQuery("SELECT u FROM User u WHERE u.email = :username", User.class);
		query.setParameter("username", username);
		List<User> users = query.getResultList();
		if (users == null || users.size() == 0) {
			flash().put("user-not-existing", "yes");
			return null;
		}
		User user = users.get(0);
		return user;
	}
	
	@Transactional
	public static Result userForm() {
		return ok(userForm.render(UserController.getCurrentUser()));
	}
	
	@Transactional
	public static Result newUser() {
		
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		String firstname = form.get("firstname")[0];
		String surname = form.get("surname")[0];
		String[] emails = form.get("email");
		String[] passwords = form.get("password");
		
		boolean firstnameEmpty = firstname.equals("");
		boolean surnameEmpty = surname.equals("");
		boolean emailEmpty = emails[0].equals("");
		boolean passwordEmpty = passwords[0].equals("");
		
		if (firstnameEmpty || surnameEmpty || emailEmpty || passwordEmpty) {
			flash().put("fields-left-empty", "yes");
			return redirect("/register");
		}
		
		boolean emailsMatch = emails[0].equals(emails[1]); 
		boolean passwordsMatch = passwords[0].equals(passwords[1]);
		
		if (!emailsMatch || !passwordsMatch) {
			if (!emailsMatch) {				
				flash().put("email-not-matching", "yes");
			}
			if (!passwordsMatch) {
				flash().put("password-not-matching", "yes");
			}
			return redirect("/register");
		}
		
		 TypedQuery<User> query = JPA.em().createQuery("SELECT u FROM User u WHERE u.email = :username", User.class);
		 query.setParameter("username", emails[0]);
		List<User> users = query.getResultList();
		if (users == null ||users.size() > 0) {
			flash().put("username-exists", "yes");
			return redirect("/register");
		}
		User user = new User(firstname, surname, emails[0], passwords[0], UserStatus.CUSTOMER);
		
		JPA.em().persist(user);
		
		session().put("username", emails[0]);
		
		return redirect("/");
	}
	
}
