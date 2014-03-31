package models;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class ECOrder {
	@Id
	@GeneratedValue
	@Column(name="ID")
	public int id;
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	private Date date;
	@OneToMany(mappedBy="ecOrder")
	private List<OrderProductQuantity> opqs;
	
	public ECOrder() {
		
	}
	
	public ECOrder(User user) {
		this.date = new Date();
		this.user = user;
		this.opqs = new LinkedList<>();
		for (CartProductQuantity cpq : user.getCart()) {
			opqs.add(new OrderProductQuantity(cpq));
		}
	}
	public User getUser() {
		return user;
	}

	public Date getDate() {
		return date;
	}

	public List<OrderProductQuantity> getOpqs() {
		return opqs;
	}
	
}
