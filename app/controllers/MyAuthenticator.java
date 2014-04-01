package controllers;

import play.mvc.Result;
import play.mvc.Http;
import views.html.fourOFour;

public class MyAuthenticator extends play.mvc.Security.Authenticator {

	@Override
	public Result onUnauthorized(Http.Context ctx) {
		return ok(fourOFour.render(ctx.request().path(), null));
	}
}
