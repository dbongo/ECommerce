package models;

import java.util.List;

public class Cart {

	private final int id;
	private final User user;
	private List<ProductEntity> products;

	public Cart(int id, User user, List<ProductEntity> products) {
		this.id = id;
		this.user = user;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}
	
}
