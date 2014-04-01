package controllers;

import models.User;
import models.UserStatus;
import play.mvc.Http;

public class MyAdminAuthenticator extends MyAuthenticator {

	@Override
	public String getUsername(Http.Context ctx) {
		
		User user = UserController.getCurrentUser();
		
		if (user == null || user.getStatus() != UserStatus.ADMIN) {
			return null;
		}
		
		return ctx.session().get("username");
		
	}
	
}
