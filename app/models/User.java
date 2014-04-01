package models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.jpa.JPA;

@Entity
public class User {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	private String firstname;
	private String surname;
	private String email;
	private String password;
	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;
	@OneToMany(mappedBy="user", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<CartProductQuantity> cart;
	@OneToMany(mappedBy="user", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<ECOrder> orders;
	
	public User() {
		
	}
	
	public User(String firstname, String surname, String email, String password, UserStatus status) {
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.status = status;;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getSurname() {
		return surname;
	}

	public UserStatus getStatus() {
		return status;
	}

	public List<ECOrder> getOrders() {
		return orders;
	}
	
	public List<CartProductQuantity> getCart() {
		return cart;
	}
	
	public void placeOrder() {
		ECOrder order = new ECOrder(this);
		orders.add(order);
		cart.clear();
	}
	
	public int getNumberOfItemsInCart() {
		int items = 0;
		for (CartProductQuantity cpq : cart) {
			items += cpq.getQuantity();
		}
		return items;
	}
	
	public double getTotalPriceOfCart() {
		int price = 0;
		for (CartProductQuantity cpq : cart) {
			price += cpq.getProduct().getPrice() * cpq.getQuantity();
		}
		return price;
	}
	
	public void removeProductFromCart(Product product) {
		CartProductQuantity cpq = getCartProductQuantityForProduct(product);
		if (cpq == null) {
			return;
		}
		cart.remove(cpq);
	}
	
	public void addProductQuantity(Product product, int quantity) {
		CartProductQuantity cpq = getCartProductQuantityForProduct(product);
		int oldQuantity = 0;
		if (cpq != null) {
			oldQuantity = cpq.getQuantity();
		}
		int newQuantity = oldQuantity + quantity;
		setProductQuantityInCart(product, newQuantity);
	}
	
	public void removeProductQuantityInCart(Product product){
		cart.remove(getCartProductQuantityForProduct(product));
	}
	public void removeProductQuantityInCart(Product product, int quantity){
		CartProductQuantity cpq = getCartProductQuantityForProduct(product);
		int oldQuantity = 0;
		if (cpq != null) {
			oldQuantity = cpq.getQuantity();
		}
		int newQuantity = oldQuantity - quantity;
		if (newQuantity < 1) {
			removeProductQuantityInCart(product);
		} else {
			setProductQuantityInCart(product, newQuantity);	
		}
	}
	
	public void setProductQuantityInCart(Product product, int quantity) {
		CartProductQuantity cpq = getCartProductQuantityForProduct(product);
		if (cpq == null) {
			cpq = new CartProductQuantity(product, quantity, this);
			this.cart.add(cpq);
		} else {
			cpq.setQuantity(quantity);
		}
		
	}
	
	private CartProductQuantity getCartProductQuantityForProduct(Product product) {
		for (CartProductQuantity cpq : this.cart) {
			if (cpq.getProduct().getId() == product.getId()) {
				return cpq;
			}
		}
		return null;
	}
}
