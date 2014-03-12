package models;

public class Category {
	final private int id;
	private final String name;

	public Category(int id, String name) {
		this.id = id;
		this.name = name;	
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
