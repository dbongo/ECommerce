package controllers;

import java.util.Map;

import models.Product;
import models.User;
import play.*;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class CartController extends Controller {

	@Transactional
    public static Result cart() {
		if (session().containsKey("username")) {			
			return ok(cart.render("Cart", UserController.getCurrentUser()));
		} else {
			return ok(offlineCart.render("Cart", null));
		}
    }
	
	@Transactional
	public static Result clearCart() {
		if (session().containsKey("username")) {
			User user = UserController.getCurrentUser();
			user.getCart().clear();
			JPA.em().persist(user);
		} 
		String previousUrl = request().getHeader("referer");
		return ok();	
	}

	@Transactional
	public static Result mergeCart() {
		if (session().containsKey("username")) {
			return ok(mergeCarts.render(UserController.getCurrentUser()));
		}
		return redirect("/cart");
	}
	
	@Transactional
	@Security.Authenticated
	public static Result addProductToCart() {
		User user = UserController.getCurrentUser();
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		
		int productId =  Integer.parseInt(form.get("product")[0]);
		int quantity =  Integer.parseInt(form.get("quantity")[0]);
		Product product = JPA.em().find(Product.class, productId);
		
		if (quantity < 1 || product == null) {
			return badRequest("Product does not exsist");
		}
		user.addProductQuantity(product, quantity);
		JPA.em().persist(user);
		String previousUrl = request().getHeader("referer");
		return redirect(previousUrl);
	}
	
	@Transactional
	@Security.Authenticated
	public static Result removeProduct(int id) {
		Product product = JPA.em().find(Product.class, id);
		if (product == null) {
			return badRequest("Product does not exsist");
		}
		User user = UserController.getCurrentUser();
		user.removeProductFromCart(product);
		JPA.em().persist(user);
		String previousUrl = request().getHeader("referer");
		return redirect(previousUrl);
	}
	
}
