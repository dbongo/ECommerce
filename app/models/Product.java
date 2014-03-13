package models;

import java.util.List;

public class Product {

	private final int id;
	private final String name;
	private final String description;
	private final double price;
	private final List<Category> categories;
	private final String imgUrl;

	public Product(int id, String name, String description, String imgUrl, double price, List<Category> categories) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.imgUrl = imgUrl;
		this.price = price;
		this.categories = categories;	
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public String getImgUrl() {
		return imgUrl;
	}
}
