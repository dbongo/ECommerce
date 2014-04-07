package models;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	private int id;
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

	public int getId() {
		return id;
	}
	
	public Date getDate() {
		return date;
	}

	public List<OrderProductQuantity> getOpqs() {
		return opqs;
	}

	public double getTotalPrice() {
		double totalPrice = 0;
		for (OrderProductQuantity opq : opqs) {
			totalPrice += opq.getPricePerUnit() * opq.getQuantity();
		}
		return totalPrice;
	}
	
	public int getTotalQuantity() {
		int totalQuantity = 0;
		for (OrderProductQuantity opq : opqs) {
			totalQuantity += opq.getQuantity();
		}
		return totalQuantity;
	}
	
	public Map<String, String> getDateMap() {
		Map<String, String> dateMap = new HashMap<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		dateMap.put("year", "" + calendar.get(Calendar.YEAR));
		dateMap.put("month", String.format("%02d", (calendar.get(Calendar.MONTH) + 1)));
		dateMap.put("day", String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)));
		dateMap.put("hour", String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY)));
		dateMap.put("minute", String.format("%02d", calendar.get(Calendar.MINUTE)));
		return dateMap;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.id);
		sb.append(", ");
		sb.append(this.user.getId());
		sb.append(", ");
		sb.append(this.date);
		for (OrderProductQuantity opq : opqs) {
			sb.append('\n');
			sb.append(opq);
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
		if (other instanceof ECOrder) {
			ECOrder otherECOrder = (ECOrder)other;
			return otherECOrder.id == this.id;
		}	
		return false;
	}


}
