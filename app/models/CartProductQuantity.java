package models;

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
	int id;
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	@ManyToOne(fetch=FetchType.EAGER)
	private Product product;
	private int quantity;

	public CartProductQuantity() {
		
	}
	
	public CartProductQuantity(Product product, int quantity) {
		this.product = product;
		this.setQuantity(quantity);
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
}
