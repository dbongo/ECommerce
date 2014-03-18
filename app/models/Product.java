package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Product {

	@Id
	@Column(name="ID")
	private int id;
	private String name;
	private String description;
	private double price;
	@ManyToMany
	private List<Category> categories;
	private String imgUrl;

	public Product(int id, String name, String description, String imgUrl, double price, List<Category> categories) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.imgUrl = imgUrl;
		this.price = price;
		this.categories = categories;	
	}
	
	public Product() {
		
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
