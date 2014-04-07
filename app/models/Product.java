package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	private String name;
	private String description;
	private double price;
	@ManyToMany
	private List<Category> categories;
	private String imgUrl;

	public Product(String name, String description, String imgUrl, double price, List<Category> categories) {
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.id);
		sb.append(", ");
		sb.append(this.name);
		sb.append(", ");
		sb.append(this.description);
		sb.append(", ");
		sb.append(this.imgUrl);
		sb.append(", ");
		sb.append(this.price);
		for (Category category : categories) {
			sb.append('\n');
			sb.append(category.getId());
		}
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		return id * 37;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other instanceof Product) {
			Product otherProduct = (Product)other;
			return otherProduct.id == this.id;
		}	
		return false;
	}
}
