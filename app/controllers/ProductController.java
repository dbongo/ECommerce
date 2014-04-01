package controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import com.fasterxml.jackson.databind.node.ObjectNode;

import models.Category;
import models.Product;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
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
		result.put("image", product.getImgUrl());
		result.put("id", product.getId());
		result.put("price", product.getPrice());
		
		return ok(result);
	}
	
	@Transactional
	public static Result newProduct() {
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		
		List<Category> categories = new LinkedList<>();
		
		for (String categoryId: form.get("categories")) {
			categories.add(JPA.em().find(Category.class, Integer.parseInt(categoryId)));
		}
		
		String name = form.get("name")[0];
		String description = form.get("description")[0];
		String imgName = form.get("img-name")[0];
		double price = Double.parseDouble(form.get("price")[0]);
		
		if (imgName.equals("")) {
			imgName = null;
		}
		
		Product product = new Product(name, description, imgName, price, categories);
		
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
