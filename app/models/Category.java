package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
	
	@Id
	@Column(name="ID")
	private int id;
	private String name;
	private String icon;
	
	public Category(int id, String name, String icon) {
		this.id = id;
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
