package models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	@OneToMany(mappedBy="user")
	private List<CartProductQuantity> cart;
	@OneToMany(mappedBy="user")
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

	public List<CartProductQuantity> getCart() {
		return cart;
	}
	
	public void clearCart() {
		cart = new LinkedList<>();
	}
	
	public void addProductQuantity(Product product, int quantity) {
		int oldQuantity = getCartProductQuantityForProduct(product).getQuantity();
		int newQuantity = oldQuantity + quantity;
		setProductQuantityInCart(product, newQuantity);
	}
	
	public void removeProductQuantityInCart(Product product){
		cart.remove(getCartProductQuantityForProduct(product));
	}
	public void removeProductQuantityInCart(Product product, int quantity){
		int oldQuantity = getCartProductQuantityForProduct(product).getQuantity();
		int newQuantity = oldQuantity - quantity;
		if (newQuantity < 1) {
			removeProductQuantityInCart(product);
		} else {
			setProductQuantityInCart(product, newQuantity);	
		}
	}
	
	public void setProductQuantityInCart(Product product, int quantity) {
		getCartProductQuantityForProduct(product).setQuantity(quantity);
	}
	
	private CartProductQuantity getCartProductQuantityForProduct(Product product) {
		for (CartProductQuantity cpq : cart) {
			if (cpq.getProduct().getId() == product.getId()) {
				return cpq;
			}
		}
		return null;
	}
}
