package models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

@Entity
public class ECOrder {
	@Id
	@GeneratedValue
	@Column(name="ID")
	public int id;
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date date;
	@OneToMany(mappedBy="ecOrder", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<OrderProductQuantity> opqs;
	
	public ECOrder() {
		
	}
	
	public ECOrder(User user) {
		this.user = user;
		this.opqs = new LinkedList<>();
		for (CartProductQuantity cpq : user.getCart()) {
			opqs.add(new OrderProductQuantity(cpq, this));
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
