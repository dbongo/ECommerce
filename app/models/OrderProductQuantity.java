package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OrderProductQuantity {

	@Id
	@GeneratedValue
	@Column(name="ID")
	int id;
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	@ManyToOne(fetch=FetchType.LAZY)
	private ECOrder ecOrder;
	@ManyToOne(fetch=FetchType.EAGER)
	private Product product;
	private int quantity;
	private double pricePerUnit;
	
	public OrderProductQuantity() {
		
	}
	
	public OrderProductQuantity(CartProductQuantity cpq) {
		this(cpq.getProduct(), cpq.getQuantity());
	}
	
	public OrderProductQuantity(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.pricePerUnit = product.getPrice();
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}
}
