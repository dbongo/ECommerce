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
	private int id;
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
	
	public OrderProductQuantity(CartProductQuantity cpq, ECOrder order) {
		this(cpq.getProduct(), cpq.getQuantity(), order, order.getUser());
	}
	
	public OrderProductQuantity(Product product, int quantity, ECOrder order, User user) {
		this.product = product;
		this.quantity = quantity;
		this.ecOrder = order;
		this.user = user;
		this.pricePerUnit = product.getPrice();
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

	public double getPricePerUnit() {
		return pricePerUnit;
	}
	
	@Override
	public String toString() {
		return this.id + ", " + this.user.getId() + ", " + this.ecOrder.getId() + ", " + this.product + ", " + this.quantity;
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
		if (other instanceof OrderProductQuantity) {
			OrderProductQuantity otherOrderProductQuantity = (OrderProductQuantity)other;
			return otherOrderProductQuantity.id == this.id;
		}	
		return false;
	}

}
