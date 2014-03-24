package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category {
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private int id;
	private String name;
	private String icon;
	
	public Category(String name, String icon) {
		this.name = name;
		this.icon = icon;	
	}
	
	public Category(){
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIcon() {
		return icon;
	}
}
