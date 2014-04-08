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
	private String imagePath;

	public Product(String name, String description, String imagePath, double price, List<Category> categories) {
		this.setName(name);
		this.setDescription(description);
		this.setImagePath(imagePath);
		this.setPrice(price);
		this.setCategories(categories);	
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

	public String getImagePath() {
//		if (imagePath == null || imagePath.equals("") || imagePath.equals("/assets/images/")) {
//			this.setImagePath("/assets/images/no-image.jpg");
//		} else {
//			this.setImagePath(imagePath);
//		}
		return imagePath;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.id);
		sb.append(", ");
		sb.append(this.getName());
		sb.append(", ");
		sb.append(this.getDescription());
		sb.append(", ");
		sb.append(this.getImagePath());
		sb.append(", ");
		sb.append(this.getPrice());
		if (getCategories() != null) {
			for (Category category : getCategories()) {
				sb.append('\n');
				sb.append(category.getId());
			}
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

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
