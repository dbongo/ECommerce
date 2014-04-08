package controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.springframework.format.Formatter;

import com.fasterxml.jackson.databind.node.ObjectNode;

import models.Category;
import models.Product;
import play.data.Form;
import play.data.format.Formatters;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import scala.reflect.macros.ParseException;
import views.html.*;

public class ProductController extends Controller {

	@Transactional
    public static Result allProducts() {
    	String category  = request().getQueryString("cat");
    	List<Product> prods = getAllProductsFromRepo(category);
    	String title = category == null ? "Products" : category; 
    	return ok(products.render(title, prods, UserController.getCurrentUser()));
    }

	@Transactional
    public static Result product(int id) {
    	Product prod = getProductFromRepo(id);
    	if (prod == null) {
    		return notFound("product not found");
    	}
        return ok(product.render(prod, UserController.getCurrentUser()));
    }
    
	@Transactional
	public static Result rawProduct(int id) {
		Product product = JPA.em().find(Product.class, id);
		if (product == null) {
			return notFound();
		}
		ObjectNode result = Json.newObject();
		result.put("name", product.getName());
		result.put("image", product.getImagePath());
		result.put("id", product.getId());
		result.put("price", product.getPrice());
		
		return ok(result);
	}
	
	
	@Transactional
	@Security.Authenticated(MyAdminAuthenticator.class)
	public static Result newProduct() {
		
		Formatters.register(Category.class, new Formatters.SimpleFormatter<Category>(){

			@Override
			public Category parse(String id, Locale arg1)
					throws java.text.ParseException {
				return JPA.em().find(Category.class, Integer.parseInt(id));
			}

			@Override
			public String print(Category category, Locale arg1) {
				return category.getId() + "";
			}
		});		
		
		Product product = Form.form(Product.class).bindFromRequest().get();
		JPA.em().persist(product);
		
		return redirect(routes.ProductController.allProducts());
	}
	

	@Transactional
    private static List<Product> getAllProductsFromRepo() {
		return getAllProductsFromRepo(null);
	}
	
	@Transactional
    private static List<Product> getAllProductsFromRepo(String category) {
    	List<Product> allProducts = JPA.em().createQuery("SELECT a FROM Product a", Product.class).getResultList();
    	if (category == null) {
    		return allProducts;
    	} 
    	else {
    		TypedQuery<Product> query = JPA.em().createQuery("SELECT p FROM Product p JOIN p.categories c WHERE c.name = :cat GROUP BY p", Product.class);
    		return query.setParameter("cat", category).getResultList();
    	}
    }
	
	@Transactional    
    private static Product getProductFromRepo(int id) {
    	Product product = JPA.em().find(Product.class, id);
    	return product;
    }
}
