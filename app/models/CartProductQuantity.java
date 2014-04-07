package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class CartProductQuantity {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST})
	private User user;
	@ManyToOne(fetch=FetchType.EAGER)
	private Product product;
	private int quantity;

	public CartProductQuantity() {
		
	}
	
	public CartProductQuantity(Product product, int quantity, User user) {
		this.product = product;
		this.user = user;
		this.setQuantity(quantity);
	}
	
	public int getId() {
		return id;
	}
	
	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return this.id + ", " + this.user.getId() + ", " + this.product + ", " + this.quantity;
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
		if (other instanceof CartProductQuantity) {
			CartProductQuantity otherCartProductQuantity = (CartProductQuantity)other;
			return otherCartProductQuantity.id == this.id;
		}	
		return false;
	}



}
